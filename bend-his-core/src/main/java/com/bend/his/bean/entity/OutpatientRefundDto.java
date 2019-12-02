package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "OutpatientRefundDto - 门诊退费 ", description = "门诊退费")
public class OutpatientRefundDto {

    @ApiModelProperty(notes = "退费总额")
    @JSONField(name = "退费总额")
    private String refundAmount;

    @ApiModelProperty(notes = "退费流水记录")
    @JSONField(name = "退费流水记录")
    private String refundSerialNumber;

    @ApiModelProperty(notes = "退费单号")
    @JSONField(name = "退费单号")
    private String refundCode;

}
