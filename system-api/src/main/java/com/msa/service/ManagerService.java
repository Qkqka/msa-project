package com.msa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.msa.domain.Manager;

@Service
public class ManagerService {

    public List<Manager> selectManagerList() {
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
        list.add(Manager.builder().id("admin10").name("관리자10").email("manager10@test.com").groupCode("7").build());
        return list;
    }

//    public Manager getUser(String userId) {
//        List<Manager> users = this.getUserList().stream().filter(user -> StringUtils.equals(userId, user.getId())).collect(Collectors.toList());
//        if (CollectionUtils.isEmpty(users)) {
//            return null;
//        }
//
//        return users.get(0);
//    }
}
