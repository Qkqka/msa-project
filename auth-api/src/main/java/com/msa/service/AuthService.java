package com.msa.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.msa.mapper.reader.AuthReaderMapper;
import com.msa.model.Admin;
import com.msa.model.AdminInfo;
import com.msa.model.AdminMenu;
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
    public AdminInfo selectAdminInfo(String id, String password) {
        log.info("AuthService.selectAdminInfo: {}", id);
        CommonUtil.checkParam(id, password);

        Admin admin = this.authReaderMapper.selectAdminInfo(id);
        if (admin == null){
            return null;
        }

        String cryptPassword = Sha512.sha512(password, password);
        if (!StringUtils.equals(cryptPassword, admin.getAdminPw())) {
            return null;
        }

        // password 초기화
        admin.setAdminPw(null);

        // 접근 가능 메뉴 목록 조회
        List<AdminMenu> adminMenuList = this.authReaderMapper.selectAdminMenuList(admin.getAdminSeq());

        AdminInfo adminInfo = new AdminInfo();
        adminInfo.setAdmin(admin);
        adminInfo.setAdminMenuList(adminMenuList);

        return adminInfo;
    }

}
