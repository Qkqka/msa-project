package com.msa.mapper.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.msa.annotation.ReaderMapper;
import com.msa.model.Code;
import com.msa.model.CodeList;
import com.msa.model.CodeSearch;

/**
 * 코드 reader mapper
 * 
 * @author fnfnksb@gmail.com
 */
@ReaderMapper
public interface CodeReaderMapper {

    List<CodeList> selectCodeList(CodeSearch param);

    int selectCodeListCount(CodeSearch param);

    Code selectCode(@Param("codeGrp") String codeGrp, @Param("code") String code);

}
