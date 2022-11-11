package com.msa.model;

import com.msa.model.BaseDomain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class Manager extends BaseDomain {

    /**
     * 
     */
    private static final long serialVersionUID = 2981664331697295538L;

    private String email;
    private String name;
    private String id;
    private String groupCode;

}
