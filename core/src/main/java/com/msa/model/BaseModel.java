package com.msa.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseModel extends CommonModel {

    private static final long serialVersionUID = -1340647457878045899L;

    /**
     * 등록일시
     */
    private String regDt;

    /**
     * 수정일시
     */
    private String modDt;

    /**
     * 등록자
     */
    private long regSeq;

    /**
     * 수정자
     */
    private long modSeq;

}
