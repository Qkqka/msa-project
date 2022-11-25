package com.msa.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.msa.mapper.reader.AuthReaderMapper;
import com.msa.model.AuthInfo;
import com.msa.util.CommonUtil;
import com.msa.util.Sha512;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 인증 service
 * @author fnfnksb@gmail.com
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthReaderMapper authReaderMapper;

    /**
     * 관리자 정보 조회
     * @param id
     * @param password
     * @return
     */
    public AuthInfo selectAdminInfo(String id, String password) {
        log.info("AuthService.selectAdminInfo: {}", id);
        //CommonUtil.checkParam();

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
