package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * [ {  "AccName":"银联卡",
 * "Payment":"银联卡",
 * "PayPy":"YLK",
 * "AccPy":"YLK",
 * "PaymentID":"78556537BF761787E0539401A8C0A6C8",
 * "IsDefautShow":0,
 * "ID":"909C397F192B09BDE053D65110ACA427",
 * "SortNo":3
 * }]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PayAccountBO - 机构支付账户",description = "机构支付账户")
public class PayAccountBO {

    @ApiModelProperty(notes = "账户名字")
    @JSONField(name = "AccName")
    private String accName;

    @ApiModelProperty(notes = "支付类型")
    @JSONField(name = "Payment")
    private String payment;

    @ApiModelProperty(notes = "拼音简写")
    @JSONField(name = "PayPy")
    private String payPy;

    @ApiModelProperty(notes = "账户拼音简写")
    @JSONField(name = "AccPy")
    private String accPy;

    @ApiModelProperty(notes = "支付方式ID")
    @JSONField(name = "PaymentID")
    private String paymentId;

    @ApiModelProperty(notes = "默认显示")
    @JSONField(name = "IsDefautShow")
    private String isDefaultShow;

    @ApiModelProperty(notes = "账户ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "账户ID")
    @JSONField(name = "OrgAccID")
    private String orgAccID;

    @ApiModelProperty(notes = "金额")
    @JSONField(name = "Fee")
    private BigDecimal fee;
}
