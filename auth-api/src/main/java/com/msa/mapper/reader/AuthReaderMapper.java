package com.msa.mapper.reader;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.msa.mapper.entity.AdminEntity;

@Mapper
public interface AuthReaderMapper {

    AdminEntity selectManagerPassword(@Param("id") String id);

}
