package com.msa.mapper.reader;

import org.apache.ibatis.annotations.Param;

import com.msa.annotation.ReaderMapper;
import com.msa.model.AuthInfo;

/**
 * 인증 reader mapper
 * @author fnfnksb@gmail.com
 */
@ReaderMapper
public interface AuthReaderMapper {

    AuthInfo selectAdminInfo(@Param("id") String id);

}
