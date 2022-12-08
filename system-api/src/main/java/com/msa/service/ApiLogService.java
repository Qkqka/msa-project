package com.msa.service;

import org.springframework.stereotype.Service;

import com.msa.mapper.writer.ApiLogWriterMapper;
import com.msa.model.ApiLogEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApiLogService {

    private final ApiLogWriterMapper apiLogWriterMapper;

    public void insertApiLog(ApiLogEvent apiLogEvent) {
        this.apiLogWriterMapper.insertApiLog(apiLogEvent);
    }

}
