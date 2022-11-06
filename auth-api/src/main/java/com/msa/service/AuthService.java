package com.msa.service;

import com.msa.client.CommonCodeFeignClient;
import com.msa.domain.AuthInfo;
import com.msa.domain.Manager;
import com.msa.domain.ManagerLogin;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private CommonCodeFeignClient commonCodeFeignClient;

    public List<Manager> getUserList() {
        List<Manager> list = new ArrayList<>();
        list.add(Manager.builder().id("admin1").name("관리자1").email("manager1@test.com").groupCode("1").build());
        list.add(Manager.builder().id("admin2").name("관리자2").email("manager2@test.com").groupCode("1").build());
        list.add(Manager.builder().id("admin3").name("관리자3").email("manager3@test.com").groupCode("2").build());
        list.add(Manager.builder().id("admin4").name("관리자4").email("manager4@test.com").groupCode("2").build());
        list.add(Manager.builder().id("admin5").name("관리자5").email("manager5@test.com").groupCode("3").build());
        list.add(Manager.builder().id("admin6").name("관리자6").email("manager6@test.com").groupCode("4").build());
        list.add(Manager.builder().id("admin7").name("관리자7").email("manager7@test.com").groupCode("5").build());
        list.add(Manager.builder().id("admin8").name("관리자8").email("manager8@test.com").groupCode("5").build());
        list.add(Manager.builder().id("admin9").name("관리자9").email("manager9@test.com").groupCode("6").build());
        list.add(Manager.builder().id("admin10").name("관리자10").email("manager1@test.com").groupCode("7").build());

        return list;
    }

    public Manager getUser(String userId) {
        List<Manager> users = this.getUserList().stream().filter(user -> StringUtils.equals(userId, user.getId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(users)) {
            return null;
        }

        return users.get(0);
    }

    public AuthInfo login(ManagerLogin loginInfo) {
        List<Manager> userList = this.getUserList().stream().filter(user -> StringUtils.equals(user.getId(), loginInfo.getId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userList)){
            throw new IllegalArgumentException("로그인 정보를 다시 확인해주세요.");
        }

        if (!StringUtils.equals("pw1", loginInfo.getPassword())) {
            throw new IllegalArgumentException("로그인 정보를 다시 확인해주세요.");
        }

        Manager user = userList.get(0);

        return AuthInfo.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .groupCode(user.getGroupCode())
                .build();
    }

}
