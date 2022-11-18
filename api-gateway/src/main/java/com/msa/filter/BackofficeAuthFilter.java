package com.msa.filter;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.msa.exception.AdminAuthException;
import com.msa.model.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * 게이트웨이 인증 필터
 * @author fnfnksb@gmail.com
 */
@Slf4j
@Component
public class BackofficeAuthFilter extends AbstractGatewayFilterFactory<BackofficeAuthFilter.Config> {

    @Autowired
    private ReactorLoadBalancerExchangeFilterFunction loadbalancerexchangefilter;

    @Value("${application.auth.admin-except-url-list}")
    private List<String> adminExceptUrlList;

    @Value("${application.web.back.access-key}")
    private String accessKey;

    @Value("${application.web.back.access-value}")
    private String accessValue;

    public BackofficeAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 요청 url
            String url = request.getURI().getPath();

            // checkUrl 체크
            boolean isAdminCheck = true;

            log.info("url: {}", url);
            log.info("adminExceptUrlList: {}", this.adminExceptUrlList);
            if (!CollectionUtils.isEmpty(this.adminExceptUrlList)) {
                // url의 정확한 매칭 체크
                isAdminCheck = this.adminExceptUrlList.stream().anyMatch(chkUrl -> StringUtils.equals(url, chkUrl)) ? false : true;

                // url의 *문자열 체크
                //  - 앞에서 false가 되지 않았다면 체크함.
                if (isAdminCheck) {
                    isAdminCheck = this.adminExceptUrlList.stream()
                            .filter(chkUrl -> StringUtils.endsWith(chkUrl, "*"))
                            .map(chkUrl -> StringUtils.substringBeforeLast(chkUrl, "*"))
                            .anyMatch(chkUrl -> StringUtils.startsWith(url, chkUrl)) ? false : true;
                }
            }

            if ( !isAdminCheck ) {
                // 엑세스 토큰 설정해야함
                return chain.filter(exchange);

            } else {
                // 헤더 정보
                HttpHeaders headers = request.getHeaders();

                // 요청 url -> try~catch로 감싼이유?
                String api = request.getPath().value();
                log.info("api : {}", api);

                // 관리자 체크 로직 필요한 경우와 그냥 통과하는 부분을 구분해서 처리함.
                WebClient wc = WebClient.builder()
                        .filter(this.loadbalancerexchangefilter)
                        .baseUrl("lb://auth-api")
                        .build();

                return wc.get()
                        .uri("check?api=" + api)
                        .headers(header -> {
                            // 쿠키이관
                            if (!CollectionUtils.isEmpty(headers.get("Cookie"))) {
                                header.addAll("Cookie", headers.get("Cookie"));
                            }

                            // 엑세스토큰 설정
                            header.add(accessKey, accessValue);
                        })
                        .retrieve()
                        .bodyToMono(Result.class)
                        .map(result -> {
                            // 로그인체크
                            if (result.getResultCode() == -1) {
                                throw new AdminAuthException(result.getResultCode(), result.getResultMsg());

                            // 권한체크
                            } else if (result.getResultCode() == -2) {
                                throw new AdminAuthException(result.getResultCode(), result.getResultMsg());
                            }

                            return exchange;
                        })
                        .flatMap(chain::filter);
            }
        });
    }

    public static class Config {
    }
}
