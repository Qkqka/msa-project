package com.msa.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

//@Component
public class FilterConfig {

//    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> 
                    r.path("/cmm/**")
                     .filters(f -> f.addRequestHeader("system-api-request", "system-api-request-header")
                             .addResponseHeader("system-api-response", "system-api-response-header"))
                     .uri("http://localhost:8100"))
                .route(r -> 
                    r.path("/prd/**")
                     .filters(f -> f.addRequestHeader("product-api-request", "product-api-request-header")
                             .addResponseHeader("product-api-response", "product-api-response-header"))
                     .uri("http://localhost:8300"))
                .build();
    }
}
