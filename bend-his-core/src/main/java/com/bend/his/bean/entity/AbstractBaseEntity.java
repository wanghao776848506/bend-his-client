package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class AbstractBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "交易编号")
    @JSONField(name = "交易编号")
    protected String tradeCode;

    @ApiModelProperty(notes = "验证码/授权码")
    @JSONField(name = "验证码")
    protected String authCode;



    public String createJSONObject(){
        return "";
    }
}