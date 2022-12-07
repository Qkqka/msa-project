package com.msa.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.msa.exception.CustomException;
import com.msa.mapper.reader.AuthReaderMapper;
import com.msa.model.AdminInfo;
import com.msa.util.CommonUtil;
import com.msa.util.HashUtil;

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
    public AdminInfo selectAdminInfo(String id, String password) {
        log.info("AuthService.selectAdminInfo: {}", id);
        CommonUtil.checkParam(id, password);

        AdminInfo adminInfo = this.authReaderMapper.selectAdminInfo(id);
        if (adminInfo == null){
            throw new CustomException(121212, "관리자 정보가 없습니다.");
        }

        String cryptPassword = HashUtil.sha512(password, password);
        if (!StringUtils.equals(cryptPassword, adminInfo.getAdminPw())) {
            throw new CustomException(121213, "관리자 정보를 다시 확인해주세요.");
        }

        // password 초기화
        adminInfo.setAdminPw(null);

        // 접근 가능 메뉴 목록 조회 및 세팅
        adminInfo.setAdminMenuList(this.authReaderMapper.selectAdminMenuList(adminInfo.getAdminSeq()));

        return adminInfo;
    }

}
