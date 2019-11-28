package com.bend.his.bean.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    public String getInputParameter() {
        return "";
    }
}