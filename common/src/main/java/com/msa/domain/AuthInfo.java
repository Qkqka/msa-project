package com.msa.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Builder
@Data
@RedisHash(value = "authInfo", timeToLive = 60*5)
public class AuthInfo implements Serializable {
    private static final long serialVersionUID = 2981664331691295538L;

    private String email;
    private String name;
    private String id;
    private String groupCode;
}
