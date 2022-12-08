package com.msa.mapper.writer;

import com.msa.annotation.WriterMapper;
import com.msa.model.Code;

/**
 * 코드 writer mapper
 * 
 * @author fnfnksb@gmail.com
 */
@WriterMapper
public interface CodeWriterMapper {

    int insertCode(Code code);

    int updateCode(Code code);

}
