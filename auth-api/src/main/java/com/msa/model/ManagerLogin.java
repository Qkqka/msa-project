package com.msa.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ManagerLogin extends BaseDomain {
    /**
     * 
     */
    private static final long serialVersionUID = -7180204751228964200L;

    @NotEmpty
    private String id;

    @NotEmpty
    private String password;
}
