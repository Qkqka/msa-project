package com.msa.filter;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class BackofficeAuthFilter extends AbstractGatewayFilterFactory<BackofficeAuthFilter.Config> {

//    private ReactorLoadBalancerExchangeFilterFunction loadbalancerexchangefilter;

    public BackofficeAuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest clientRequest = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("BackofficeAuthFilter PRE filter: request id -> {}", clientRequest.getId());
            log.info("BackofficeAuthFilter PRE filter: request header -> {}", clientRequest.getHeaders());
            log.info("BackofficeAuthFilter PRE filter: request cookie -> {}", clientRequest.getCookies());

            WebClient wc = WebClient.builder()
                    .baseUrl("http://localhost:9100") // lb://auth-api
//                    .filter((request, next) -> {
//                        ClientRequest filtered = ClientRequest.from(request).header("cookie", clientRequest.getHeaders().get("cookie").get(0)).build();
//                        return next.exchange(filtered);
//                    })
                    .defaultHeader(HttpHeaders.COOKIE, clientRequest.getHeaders().get("cookie").get(0))
                    .build();
            String authYn = wc.get()
                .uri("/auth/auth_check")
                .retrieve()
                .bodyToMono(String.class)
                .map(e -> {
                  log.debug("BackofficeAuthFilter filter authYn: {}", e);
                  if (StringUtils.equals("N", e)) {
                      throw new RuntimeException("로그인 해주세요.");
                  }
                  return e;
                })
                .block(); // 동기로 바꾸면 안됨.
//                .subscribe(e -> {
//                    log.debug("BackofficeAuthFilter filter authYn: {}", e);
//                    if (StringUtils.equals("N", e)) {
//                        throw new RuntimeException("로그인 해주세요.");
//                    }
//                });

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("BackofficeAuthFilter POST filter: response code -> {}, authYn: {}", response.getStatusCode(), authYn);
            }));
        });
    }

    public static class Config {
        
    }
}
