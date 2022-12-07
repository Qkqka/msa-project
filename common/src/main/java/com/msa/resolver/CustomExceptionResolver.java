package com.msa.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.msa.exception.AdminAuthException;
import com.msa.exception.CustomException;
import com.msa.model.Result;

import lombok.extern.slf4j.Slf4j;

/**
 * Custom Exception Resolver
 * 
 * @author fnfnksb@gmail.com
 *
 */
@Slf4j
@Order(-1)
//@Component
public class CustomExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        log.error("CustomExceptionResolver.throwable", ex);

        Result result = null;
        String exName = ex.getClass().getName();

        if (ex instanceof AdminAuthException) {
            log.info("사용자 정의 exception : {}", exName);
            AdminAuthException exception = (AdminAuthException) ex;
            result = new Result(exception.getResultCode(), exception);

        } else if (ex instanceof CustomException) {
            log.info("사용자 정의 exception : {}", exName);
            CustomException exception = (CustomException) ex;
            result = new Result(exception.getResultCode(), exception);

        } else {
            log.info("사용자 정의 exception 아님 : {}", exName);
            result = new Result(HttpStatus.BAD_REQUEST.value());
        }

        log.error("CustomExceptionResolver.Result: {}", result);

        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        mv.addObject("data", result);

        return mv;
    }

}
