package com.msa.config.api;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.msa.config.ApplicationYAMLConfig;
import com.msa.resolver.AdminInfoArgumentResolver;
import com.msa.resolver.CustomExceptionResolver;

import lombok.RequiredArgsConstructor;

/**
 * api 서버 공통 mvc config
 * 
 * @author fnfnksb@gmail.com
 */
@Configuration
@RequiredArgsConstructor
public class ApiMvcConfig extends WebMvcConfigurationSupport {

    private final ApplicationYAMLConfig applicationYAMLConfig;

    /**
     * Argument Resolver 등록
     */
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new AdminInfoArgumentResolver(this.applicationYAMLConfig));
    }

    /**
     * Custom Exception Resolver 등록
     */
    @Override
    protected void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
        exceptionResolvers.add(new CustomExceptionResolver());
    }
}
