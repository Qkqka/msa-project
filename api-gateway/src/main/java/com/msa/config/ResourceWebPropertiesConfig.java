package com.msa.config;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AbstractErrorWebExceptionHandler를 사용하기 위한 resource를 등록함.
 * @author fnfnksb@gmail.com
 */
@Configuration
public class ResourceWebPropertiesConfig {

    @Bean
    public WebProperties.Resources resources() {
        return new WebProperties.Resources();
    }

}