package com.msa.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommonCode {
    private String codeId;
    private String codeName;
}
