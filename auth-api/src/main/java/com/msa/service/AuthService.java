package com.msa.service;

import com.msa.domain.AuthInfo;
import com.msa.model.ManagerLogin;

public interface AuthService {

    AuthInfo login(ManagerLogin loginInfo);

}
