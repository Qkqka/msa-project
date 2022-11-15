package com.msa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//@ComponentScan(basePackages = "", excludeFilters = Filter.)
@EnableRedisHttpSession
@EnableDiscoveryClient
@SpringBootApplication(
    exclude={DataSourceAutoConfiguration.class} // datasource 설정이 없는 경우 해당 어노테이션 사용하면 정상 기동됨
)
public class BackofficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackofficeApplication.class, args);
    }

}
