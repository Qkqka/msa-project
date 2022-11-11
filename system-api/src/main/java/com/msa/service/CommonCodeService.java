package com.msa.service;

import java.util.List;

import com.msa.model.CommonCode;

public interface CommonCodeService {

    CommonCode selectCode(String codeId);

    List<CommonCode> selectCodeList();
}
