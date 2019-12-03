package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.PayAccountBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 医院机构支付方式
 */
@Data
@ApiModel(value = "HospitalPaymentDto - 医院机构支付方式", description = "医院机构支付方式")
public class HospitalPaymentDto extends AbstractBaseEntity {
    /*response data*/
    @ApiModelProperty(notes = "支付方式ID")
    @JSONField(name = "PaymentID")
    private String paymentId;

    @ApiModelProperty(notes = "支付方式名称")
    @JSONField(name = "Payment")
    private String paymentName;

    @ApiModelProperty(notes = "拼音简写")
    @JSONField(name = "PayPy")
    private String payPy;

    @ApiModelProperty(notes = "账户列表")
    @JSONField(name = "OrgAcc")
    private List<PayAccountBO> orgAcc;

    @ApiModelProperty(notes = "选中的账户ID,app/公众号程序可以不用理会该值")
    @JSONField(name = "selectedAcc")
    private String selectedAccId; //org.tempuri.bean.bo.PayAccountBO.id
}
