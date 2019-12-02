package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@ApiModel
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "验证码/授权码/产品验证码")
    @JSONField(name = "验证码")
    protected String authCode;

    @ApiModelProperty(hidden = true)
    public String getInputParameter() {
        return "";
    }
}