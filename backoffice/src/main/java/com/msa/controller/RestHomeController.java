package com.msa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


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
//        log.info("session.adminInfo", session.getAttribute("adminInfo"));
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
