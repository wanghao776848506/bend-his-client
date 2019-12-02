package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 住院预交费
 */
@Getter
@Setter
@ApiModel(value = "PrepaymentDto - 住院预交费记录", description = "住院预交费记录")
public class PrepaymentRecordDto {

    @ApiModelProperty(notes = "ID/住院预交费ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "缴费时间")
    @JSONField(name = "缴费时间")
    private String payTime;

    @ApiModelProperty(notes = "缴费金额")
    @JSONField(name = "缴费金额")
    private String paymentAmount;

}
