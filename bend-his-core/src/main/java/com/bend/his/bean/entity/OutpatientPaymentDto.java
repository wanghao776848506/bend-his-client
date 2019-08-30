package com.bend.his.bean.entity;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.PayAccountBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 门诊缴费
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "门诊缴费")
public class OutpatientPaymentDto extends AbstractBaseEntity {

    /*----------------*/
    @ApiModelProperty(notes = "处方ID/处方IDS,多处方ID用,分隔")
    @JSONField(name = "处方IDS")
    private String recipeIds;

    @ApiModelProperty(notes = "挂号ID")
    @JSONField(name = "挂号ID")
    private String registrationId;

    @ApiModelProperty(notes = "收费人员ID")
    @JSONField(name = "收费人员ID")
    private String userId;

    @ApiModelProperty(notes = "总金额")
    @JSONField(name = "总金额")
    private BigDecimal totalFee;

    @ApiModelProperty(notes = "缴费流水号")
    @JSONField(name = "缴费流水号")
    private String paySerialNumber;

    @ApiModelProperty(notes = "缴费方式列表,格式：[{'PaymentID':'支付方式ID','OrgAccID':'账户ID'，'Fee','金额'}]")
    @JSONField(name = "缴费方式列表")
    private List<PayAccountBO> paymentList;

    @ApiModelProperty(notes = "厂商编号或厂商唯一标识")
    @JSONField(name = "厂商唯一标识")
    private String manufacturerNumber;

    @ApiModelProperty(notes = "机构ID")
    @JSONField(name = "机构ID")
    private String organizationID;


    /*----------------*/

    @ApiModelProperty(notes = "收费记录ID")
    @JSONField(name = "收费记录ID")
    private String chargeRecordId;


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("处方IDS", this.getRecipeIds());
        inputJson.put("收费人员ID", this.getUserId());
        inputJson.put("机构ID", this.getOrganizationID());
        inputJson.put("挂号ID", this.getRegistrationId());
        inputJson.put("总金额", this.getTotalFee());
        inputJson.put("缴费流水号", this.getPaySerialNumber());
        inputJson.put("缴费方式列表", this.getPaymentList());
        inputJson.put("厂商唯一标识", this.getManufacturerNumber());
        return inputJson.toJSONString();
    }

}
