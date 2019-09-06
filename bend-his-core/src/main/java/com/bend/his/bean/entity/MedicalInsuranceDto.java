package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 医保信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MedicalInsuranceDto - 医保信息", description = "医保信息")
public class MedicalInsuranceDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "业务ID")
    @JSONField(name = "业务ID")
    private String businessId;

    @ApiModelProperty(notes = "医保卡号")
    @JSONField(name = "医保卡号")
    private String insuranceNo;

    @ApiModelProperty(notes = "总费用/医保总费用")
    @JSONField(name = "医保总费用")
    private BigDecimal insuranceTotalFee;

    @ApiModelProperty(notes = "报账费用")
    @JSONField(name = "报账费用")
    private BigDecimal reimburseFee;

    @ApiModelProperty(notes = "自付费用")
    @JSONField(name = "自付费用")
    private BigDecimal selfPayFee;

    @ApiModelProperty(notes = "其他信息[其他信息<JSON格式>],如：{'统筹支付':'统筹支付','起付线':'起付线'}")
    @JSONField(name = "其他信息")
    private String otherInfo;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("业务ID", this.getBusinessId());
        inputJson.put("医保卡号", this.getInsuranceNo());
        inputJson.put("医保总费用", this.getInsuranceTotalFee());
        inputJson.put("报账费用", this.getReimburseFee());
        inputJson.put("自付费用", this.getSelfPayFee());
        inputJson.put("其他信息", this.getOtherInfo());
        return inputJson.toJSONString();
    }
}
