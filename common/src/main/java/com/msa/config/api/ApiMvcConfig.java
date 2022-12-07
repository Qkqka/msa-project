package com.msa.config.api;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
public class ApiMvcConfig implements WebMvcConfigurer {

    private final ApplicationYAMLConfig applicationYAMLConfig;

    /**
     * AdminInfoArgumentResolver 등록
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(new AdminInfoArgumentResolver(this.applicationYAMLConfig));
    }

    /**
     * CustomExceptionResolver 등록
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        WebMvcConfigurer.super.configureHandlerExceptionResolvers(resolvers);
        resolvers.add(new CustomExceptionResolver());
    }

//    @Bean
//    public FilterRegistrationBean<Filter> bodyFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
//        filterRegistrationBean.setFilter(new BodyFilter());
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.addUrlPatterns("/*");
//
//        return filterRegistrationBean;
//    }

}
