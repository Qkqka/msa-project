package com.msa.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseController {

    private final HttpServletRequest request;

}
