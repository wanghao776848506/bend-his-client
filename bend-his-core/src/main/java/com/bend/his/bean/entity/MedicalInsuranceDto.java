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

    /*接口38参数*/
//    @ApiModelProperty(notes = "业务ID")
//    @JSONField(name = "业务ID")
//    private String businessId;

    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构ID")
    private String organizationCode;

    @ApiModelProperty(notes = "操作人ID/操作员ID")
    @JSONField(name = "操作人ID")
    private String userId;

    @ApiModelProperty(notes = "HIS调用医保交易动作产生的唯一ID")
    @JSONField(name = "发起交易的动作ID")
    private String dealBusinessId;

    @ApiModelProperty(notes = "入参")
    @JSONField(name = "入参")
    private String inputParam;

    @ApiModelProperty(notes = "出参")
    @JSONField(name = "出参")
    private String outParam;

    @ApiModelProperty(notes = "医保返回的业务号")
    @JSONField(name = "医保返回的业务号")
    private String dealBusinessNum;

    @ApiModelProperty(notes = "医保交易码")
    @JSONField(name = "医保交易码")
    private String dealCode;


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
        /*38接口*/
//        inputJson.put("业务ID", this.getBusinessId());
        inputJson.put("机构ID", this.getOrganizationCode());
        inputJson.put("操作人ID", this.getUserId());
        inputJson.put("发起交易的动作ID", this.getDealBusinessId());
        inputJson.put("入参", this.getInputParam());
        inputJson.put("出参", this.getOutParam());
        inputJson.put("医保返回的业务号", this.getDealBusinessNum());
        inputJson.put("医保交易码", this.getDealCode());
        return inputJson.toJSONString();
    }
}
