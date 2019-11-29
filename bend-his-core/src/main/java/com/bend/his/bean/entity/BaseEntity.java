package com.bend.his.bean.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(hidden = true)
    public String getInputParameter() {
        return "";
    }
}