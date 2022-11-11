package com.msa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // 런타임까지 어노테이션 정보가 남아있게
@Target(ElementType.PARAMETER) // 파라미터에만 사용
public @interface AuthInfo {

}
