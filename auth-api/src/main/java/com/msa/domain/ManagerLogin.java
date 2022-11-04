package com.msa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ManagerLogin extends BaseDomain{
    /**
     * 
     */
    private static final long serialVersionUID = -7180204751228964200L;

    private String id;
    private String password;
}
