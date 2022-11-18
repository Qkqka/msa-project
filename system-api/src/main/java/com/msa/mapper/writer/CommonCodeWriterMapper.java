package com.msa.mapper.writer;

import com.msa.annotation.WriterMapper;
import com.msa.model.CommonCode;

/**
 * 공통코드 writer mapper
 * 
 * @author fnfnksb@gmail.com
 */
@WriterMapper
public interface CommonCodeWriterMapper {

    int insertCommonCode(CommonCode commonCode);

    int updateCommonCode(CommonCode commonCode);

}
