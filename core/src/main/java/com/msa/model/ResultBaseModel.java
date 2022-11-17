package com.msa.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResultBaseModel<T> {

    private List<T> dataList;

    private T data;
}
