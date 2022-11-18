package com.msa.mapper.writer;

import com.msa.annotation.WriterMapper;
import com.msa.model.Code;

/**
 * 공통코드 writer mapper
 * 
 * @author fnfnksb@gmail.com
 */
@WriterMapper
public interface CommonCodeWriterMapper {

    int insertCommonCode(Code commonCode);

    int updateCommonCode(Code commonCode);

}
