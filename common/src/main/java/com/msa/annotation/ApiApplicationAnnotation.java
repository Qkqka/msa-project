package com.msa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableAspectJAutoProxy // aop 사용 - @Aspect를 찾을 수 있게 해줌
@EnableRedisHttpSession // redis session 사용
@EnableDiscoveryClient  // eureka client 사용
@SpringBootApplication
@Target(ElementType.TYPE) // class, interface, enum에만 적용
public @interface ApiApplicationAnnotation {

}
