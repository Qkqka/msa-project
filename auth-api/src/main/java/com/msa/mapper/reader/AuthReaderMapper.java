package com.msa.mapper.reader;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.msa.annotation.ReaderMapper;
import com.msa.model.Admin;
import com.msa.model.AdminMenu;

/**
 * 인증 reader mapper
 * @author fnfnksb@gmail.com
 */
@ReaderMapper
public interface AuthReaderMapper {

    Admin selectAdminInfo(@Param("id") String id);

    List<AdminMenu> selectAdminMenuList(long adminSeq);

}
