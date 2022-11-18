package com.msa.mapper.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.msa.annotation.ReaderMapper;
import com.msa.model.Code;
import com.msa.model.CommonCodeList;
import com.msa.model.CommonCodeSearch;

/**
 * 공통코드 reader mapper
 * 
 * @author fnfnksb@gmail.com
 */
@ReaderMapper
public interface CommonCodeReaderMapper {

    List<CommonCodeList> selectCodeList(CommonCodeSearch param);

    int selectCodeListCount(CommonCodeSearch param);

    Code selectCode(@Param("codeGrp") String codeGrp, @Param("code") String code);

}
