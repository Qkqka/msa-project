package com.msa.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msa.model.Manager;
import com.msa.service.ManagerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RestManagerController {

    private final ManagerService managerService;

    @GetMapping("/manager/list")
    public ResponseEntity<?> getManagerList(HttpSession session) throws IllegalAccessException {
        Object authInfo = session.getAttribute("authInfo");
        log.info("session.authInfo: {}", authInfo);
        if (authInfo == null) {
            throw new IllegalAccessException("조회 권한이 없습니다.");
        }
        List<Manager> list = this.managerService.selectManagerList();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

//    @GetMapping("/user/{userId}")
//    public ResponseEntity<Manager> getUser(@PathVariable("userId") String userId) {
//        log.info("RestAuthController.getUser: {}", userId);
//
//        return ResponseEntity.status(HttpStatus.OK).body(authService.getUser(userId));
//    }
}
