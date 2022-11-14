package com.msa.service;

import java.util.List;

import com.msa.model.CommonCode;
import com.msa.model.CommonCodeList;

public interface CommonCodeService {

    CommonCode selectCode(String codeId);

    List<CommonCodeList> selectCodeList();
}
