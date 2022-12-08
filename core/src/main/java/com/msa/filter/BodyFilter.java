package com.msa.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.web.filter.OncePerRequestFilter;

import com.msa.wrapper.HttpServletRequestBodyWrapper;
import com.msa.wrapper.HttpServletResponseBodyWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * request/response body 한번만 꺼낼 수 있음( 그걸 처리하기 위함 )
 * @author fnfnksb@gmail.com
 */
@Slf4j
//@Component
public class BodyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 정적 리소스 제외 처리 해야함
        log.info("BodyFilter.request.getRequestURI : {}", request.getRequestURI());
//        if (request.getRequestURI().matches("")) {
//            return;
//        }

        HttpServletRequestWrapper requestWrapper = new HttpServletRequestBodyWrapper(request);
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseBodyWrapper(response);

        filterChain.doFilter(requestWrapper, responseWrapper);
    }
}
