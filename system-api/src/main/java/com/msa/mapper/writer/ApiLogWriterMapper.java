package com.msa.mapper.writer;

import com.msa.annotation.WriterMapper;
import com.msa.model.ApiLogEvent;

@WriterMapper
public interface ApiLogWriterMapper {

    int insertApiLog(ApiLogEvent apiLogEvent);

}
