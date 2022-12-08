package com.msa.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.msa.model.ApiLogEvent;
import com.msa.service.ApiLogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAsync
@RequiredArgsConstructor
public class SystemApiLogEventListener {

    private final ApiLogService apiLogService;

    @Async
    @EventListener
    public void insertApiLog(ApiLogEvent apiLogEvent) {
        log.info("SystemApiLogEventListener.insertApiLog.apiLogEvent : {}", apiLogEvent);
        this.apiLogService.insertApiLog(apiLogEvent);
    }
}
