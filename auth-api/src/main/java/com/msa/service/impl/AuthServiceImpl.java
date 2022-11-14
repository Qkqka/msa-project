package com.msa.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.msa.crypt.Sha512;
import com.msa.domain.AuthInfo;
import com.msa.mapper.entity.AdminEntity;
import com.msa.mapper.reader.AuthReaderMapper;
import com.msa.model.ManagerLogin;
import com.msa.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthReaderMapper authReaderMapper;

    public AuthInfo login(ManagerLogin loginInfo) {
        if (loginInfo == null) {
            return null;
        }

        if (StringUtils.isBlank(loginInfo.getId())) {
            return null;
        }

        if (StringUtils.isBlank(loginInfo.getPassword())) {
            return null;
        }

        AdminEntity adminInfo = this.authReaderMapper.selectManagerPassword(loginInfo.getId());
        log.debug("AuthService.login: {}", adminInfo);

        if (adminInfo == null){
            return null;
        }

        String loginPassword = loginInfo.getPassword();
        String cryptPassword = Sha512.sha512(loginPassword, loginPassword);
        log.debug("AuthService.login: {}", cryptPassword);
        if (!StringUtils.equals(cryptPassword, adminInfo.getAdminPw())) {
            return null;
        }

        return AuthInfo.builder()
                .adminSeq(adminInfo.getAdminSeq())
                .compSeq(adminInfo.getCompSeq())
                .adminId(adminInfo.getAdminId())
                .adminStatCd(adminInfo.getAdminStatCd())
                .adminTypeCd(adminInfo.getAdminTypeCd())
                .build();
    }

}
