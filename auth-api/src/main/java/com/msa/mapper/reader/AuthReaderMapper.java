package com.msa.mapper.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.msa.annotation.ReaderMapper;
import com.msa.model.AdminInfo;
import com.msa.model.AdminMenu;

/**
 * 인증 reader mapper
 * @author fnfnksb@gmail.com
 */
@ReaderMapper
public interface AuthReaderMapper {

    AdminInfo selectAdminInfo(@Param("id") String id);

    List<AdminMenu> selectAdminMenuList(@Param("adminSeq") long adminSeq);

}
