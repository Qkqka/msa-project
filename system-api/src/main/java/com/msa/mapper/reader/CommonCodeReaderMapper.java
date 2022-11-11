package com.msa.mapper.reader;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.msa.mapper.reader.entity.CommonCodeEntity;

@Mapper
public interface CommonCodeReaderMapper {

    List<CommonCodeEntity> selectCodeList();

}
