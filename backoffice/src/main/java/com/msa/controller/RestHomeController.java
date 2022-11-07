package com.msa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Slf4j
@RestController
public class RestHomeController {

    @GetMapping("/greeting")
    public String welcome() {
        return "hello, backoffice";
    }

//    @GetMapping("/")
//    public ModelAndView home(HttpSession session) {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("index");
//
//        log.info("session.id", session.getId());
//        log.info("session.authInfo", session.getAttribute("authInfo"));
//        return mv;
//    }
//
//    @GetMapping("/login")
//    public ModelAndView login() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("login");
//        return mv;
//    }
//
//    @GetMapping("/managerList")
//    public ModelAndView managerList() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("managerList");
//        return mv;
//    }
}
