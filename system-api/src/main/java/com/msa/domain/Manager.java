package com.msa.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class Manager extends BaseDomain {
    private String email;
    private String name;
    private String id;
    private String groupCode;
}
