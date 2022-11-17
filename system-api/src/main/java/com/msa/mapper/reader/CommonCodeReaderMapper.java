package com.msa.mapper.reader;

import java.util.List;

import com.msa.annotation.ReaderMapper;
import com.msa.model.CommonCodeList;

@ReaderMapper
public interface CommonCodeReaderMapper {

    List<CommonCodeList> selectCodeList();

    int selectCodeListCount();

}
