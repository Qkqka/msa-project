package com.msa.service;

import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;

import com.msa.domain.AuthInfo;
import com.msa.domain.ManagerLogin;

@Service
public class AuthService {

    public AuthInfo login(ManagerLogin loginInfo) {
        if (!StringUtils.equals("admin1", loginInfo.getId())){
//            throw new IllegalArgumentException("로그인 정보를 다시 확인해주세요.");
            return null;
        }

        if (!StringUtils.equals("pw1", loginInfo.getPassword())) {
//            throw new IllegalArgumentException("로그인 정보를 다시 확인해주세요.");
            return null;
        }

        return AuthInfo.builder()
                .id(loginInfo.getId())
                .name("관리자1")
                .email("admin1@test.com")
                .groupCode("1")
                .build();
    }

}
