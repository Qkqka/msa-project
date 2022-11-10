package com.msa.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Target(ElementType.TYPE) // class, interface, enum에만 적용
public @interface WriterMapper {

}
