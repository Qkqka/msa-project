package com.msa.config.api;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.msa.resolver.AdminInfoArgumentResolver;

import lombok.RequiredArgsConstructor;

/**
 * api 서버 공통 mvc config
 * 
 * @author fnfnksb@gmail.com
 */
@Component
@RequiredArgsConstructor
public class ApiMvcConfig extends WebMvcConfigurationSupport {

    private final AdminInfoArgumentResolver adminInfoArgumentResolver;
//    private final CustomExceptionResolver customExceptionResolver;

//    /**
//     * Argument Resolver 등록
//     */
//    @Override
//    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        super.addArgumentResolvers(argumentResolvers);
//        argumentResolvers.add(adminInfoArgumentResolver);
//    }

//    /**
//     * Custom Exception Resolver 등록
//     */
//    @Override
//    protected void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        super.extendHandlerExceptionResolvers(exceptionResolvers);
//        exceptionResolvers.add(customExceptionResolver);
//    }
}
