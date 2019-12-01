package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 门诊挂号
 */
@Getter
@Setter
@ApiModel(value = "RegistrationDto - 门诊挂号", description = "门诊挂号")
public class RegistrationDto {

    @ApiModelProperty(notes = "ID/挂号ID--返回值")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "挂号CODE")
    @JSONField(name = "CODE")
    private String registrationCode;

    @ApiModelProperty(notes = "就诊序号")
    @JSONField(name = "SEQ")
    private String seq;
}
