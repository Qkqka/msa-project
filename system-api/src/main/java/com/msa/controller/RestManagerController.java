package com.msa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.model.Admin;
import com.msa.model.Result;
import com.msa.service.ManagerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/manager")
@RequiredArgsConstructor
public class RestManagerController {

    private final ManagerService managerService;

    @GetMapping("/list")
    public Result getManagerList(HttpSession session) throws IllegalAccessException {
        Object adminInfo = session.getAttribute("adminInfo");
        log.info("session.adminInfo: {}", adminInfo);
        if (adminInfo == null) {
            throw new IllegalAccessException("조회 권한이 없습니다.");
        }
        List<Admin> list = this.managerService.selectManagerList();
        return new Result(list);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<Manager> getUser(@PathVariable("userId") String userId) {
//        log.info("RestAuthController.getUser: {}", userId);
//
//        return ResponseEntity.status(HttpStatus.OK).body(authService.getUser(userId));
//    }
}
