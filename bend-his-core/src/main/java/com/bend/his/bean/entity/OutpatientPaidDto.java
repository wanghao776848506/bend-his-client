package com.bend.his.bean.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.PayAccountBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 门诊缴费
 */
@Getter
@Setter
@ApiModel(value = "OutpatientPaidDto - 门诊已缴费列表 ", description = "门诊已缴费列表")
public class OutpatientPaidDto {
    /*返回值：key不一致的情况*/
    @ApiModelProperty(notes = "记录ID/收费记录ID")
    @JSONField(name = "记录ID")
    private String recordId;
    @ApiModelProperty(notes = "总金额")
    @JSONField(name = "总金额")
    private String totalFee;

    @ApiModelProperty(notes = "支付列表,[{'支付方式':'支付方式','金额':'金额'}]")
    @JSONField(name = "支付列表")
    private List<PayAccountBO> paymentList;
    /*与收费人员ID对应*/
    @ApiModelProperty(notes = "操作员")
    @JSONField(name = "操作员")
    private String userName;
}
