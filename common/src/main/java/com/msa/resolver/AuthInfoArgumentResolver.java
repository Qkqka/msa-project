package com.msa.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.msa.config.ApplicationYAMLConfig;
import com.msa.exception.AdminAuthException;
import com.msa.model.AuthInfo;

import lombok.RequiredArgsConstructor;

/**
 * AuthInfo Argument Resolver
 * 
 * @author fnfnksb@gmail.com
 */
@Component
@RequiredArgsConstructor
public class AuthInfoArgumentResolver implements HandlerMethodArgumentResolver {

    private final ApplicationYAMLConfig applicationYAMLConfig;

    /**
     * 호출되는 Controller 의 파라미터 값을 검사하는 콜백 함수
     * 
     * @param parameter 클라이언트로 부터 받은 파라미터 
     * @return 적용 여부
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AuthInfo.class);
    }

    /**
     * supportsParameter 콜백 함수에서 true를 반환했을 경우
     * 호출되는 콜백 함수
     * 
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession();

        AuthInfo authInfo = (AuthInfo) session.getAttribute(this.applicationYAMLConfig.getSession().getKey());
        if (authInfo == null) {
            throw new AdminAuthException(-1, "로그인해 주세요.");
        }

        return authInfo;
    }

}
