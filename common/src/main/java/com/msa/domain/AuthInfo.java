package com.msa.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthInfo implements Serializable {

    private static final long serialVersionUID = 248695190893364620L;
    private String email;
    private String name;
    private String id;
    private String groupCode;
}
