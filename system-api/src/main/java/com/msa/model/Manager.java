package com.msa.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class Manager extends BaseModel {

    private static final long serialVersionUID = 9214170872674026619L;

    private String email;
    private String name;
    private String id;
    private String groupCode;
}
