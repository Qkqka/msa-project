package com.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@ComponentScan(
        basePackages = "com.msa",  // common의 프로젝트를 읽기 위해 상위 폴더 ( 같은폴더 ) 까지 끌어올리기
        excludeFilters = @Filter(type = FilterType.REGEX, pattern = "com.msa.config.web.*")
)
@EnableAspectJAutoProxy
@EnableRedisHttpSession
@EnableDiscoveryClient
@SpringBootApplication
public class AuthApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApiApplication.class, args);
    }

}
