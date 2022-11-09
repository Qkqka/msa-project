package com.msa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.apache.ibatis.annotations.Mapper;

@Mapper
@Target(ElementType.TYPE) // class, interface, enum에만 적용
public @interface WriterMapper {

}
