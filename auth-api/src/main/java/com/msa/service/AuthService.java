package com.msa.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.msa.mapper.reader.AuthReaderMapper;
import com.msa.model.AuthInfo;
import com.msa.util.CommonUtil;
import com.msa.util.Sha512;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthReaderMapper authReaderMapper;

    public AuthInfo selectAdminInfo(String id, String password) {
        CommonUtil.checkParam();

        // 필수값 체크
        if (StringUtils.isBlank(id)) {
            return null;
        }

        if (StringUtils.isBlank(password)) {
            return null;
        }

        AuthInfo adminInfo = this.authReaderMapper.selectAdminInfo(id);
        if (adminInfo == null){
            return null;
        }

        String cryptPassword = Sha512.sha512(password, password);
        if (!StringUtils.equals(cryptPassword, adminInfo.getAdminPw())) {
            return null;
        }

        // password 초기화
        adminInfo.setAdminPw(null);

        return adminInfo;
    }

}
