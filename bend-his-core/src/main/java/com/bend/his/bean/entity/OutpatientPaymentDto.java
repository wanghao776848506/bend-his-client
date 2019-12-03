package com.bend.his.bean.entity;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门诊缴费
 */
@Data
@ApiModel(value = "OutpatientPaymentDto - 门诊缴费 ", description = "门诊缴费")
public class OutpatientPaymentDto extends AbstractBaseEntity {

    /*门诊ID在其他接口里面也就是业务ID*/
    @ApiModelProperty(notes = "业务ID/门诊ID")
    @JSONField(name = "门诊ID")
    private String businessId;
    @ApiModelProperty(notes = "收费记录ID/记录ID。可用于门诊退费入参")
    @JSONField(name = "收费记录ID")
    private String chargeRecordId;
    @ApiModelProperty(notes = "总金额/成功收费费用")
    @JSONField(name = "总费用")
    private String totalFee;
}
