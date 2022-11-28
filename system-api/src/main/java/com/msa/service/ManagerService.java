package com.msa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.msa.model.Admin;

@Service
public class ManagerService {

    public List<Admin> selectManagerList() {
        List<Admin> list = new ArrayList<>();
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
