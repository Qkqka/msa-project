package com.msa.mapper.reader;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.msa.mapper.reader.entity.AdminEntity;

@Mapper
public interface AuthReaderMapper {

    AdminEntity selectManagerPassword(@Param("id") String id);

}
