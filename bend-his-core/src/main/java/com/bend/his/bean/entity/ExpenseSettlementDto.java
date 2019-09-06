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
 * 费用结算信息--门诊、住院的结算相关信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ExpenseSettlementDto - 费用结算信息[门诊、住院]", description = "费用结算信息[门诊、住院]")
public class ExpenseSettlementDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "病人来源[1门诊 2住院]")
    @JSONField(name = "病人来源")
    private Integer patientFromType;

    @ApiModelProperty(notes = "业务ID")
    @JSONField(name = "业务ID")
    private String businessId;

    @ApiModelProperty(notes = "姓名/病人姓名/患者姓名")
    @JSONField(name = "姓名")
    private String patientName;

    @ApiModelProperty(notes = "身份证/身份证号码/患者身份证")
    @JSONField(name = "身份证")
    private String idCardNo;

    @ApiModelProperty(notes = "医保卡号")
    @JSONField(name = "医保卡号")
    private String insuranceNo;

    @ApiModelProperty(notes = "医疗类别")
    @JSONField(name = "医疗类别")
    private String medicalCategory;

    @ApiModelProperty(notes = "人员类别")
    @JSONField(name = "人员类别")
    private String personnelCategory;

    @ApiModelProperty(notes = "待遇类别")
    @JSONField(name = "待遇类别")
    private String treatmentCategory;

    @ApiModelProperty(notes = "特殊待遇类别")
    @JSONField(name = "特殊待遇类别")
    private String specialTreatmentCategory;

    @ApiModelProperty(notes = "疾病编码/疾病代码")
    @JSONField(name = "疾病代码")
    private String diseaseCode;

    @ApiModelProperty(notes = "比例取法")
    @JSONField(name = "比例取法")
    private String proportionalMethod;

    @ApiModelProperty(notes = "其他信息,JSON格式，此处必须是单引号，参考：{ '用户名':'hl','密码':'123'}")
    @JSONField(name = "其他信息")
    private String otherInfo;

    @ApiModelProperty(notes = "医保机构编码")
    @JSONField(name = "医保机构编码")
    private String medicalInsuranceAgencyCode;

    @ApiModelProperty(notes = "医保机构名称")
    @JSONField(name = "医保机构名称")
    private String medicalInsuranceAgencyName;

    @ApiModelProperty(notes = "清算方式编码")
    @JSONField(name = "清算方式编码")
    private String liquidationMethodCode;

    @ApiModelProperty(notes = "清算方式名称")
    @JSONField(name = "清算方式名称")
    private String liquidationMethodName;

    @ApiModelProperty(notes = "患者参保地编码")
    @JSONField(name = "患者参保地编码")
    private String patientEnrollmentCode;

    @ApiModelProperty(notes = "患者参保地名称")
    @JSONField(name = "患者参保地名称")
    private String patientEnrollmentName;

    @ApiModelProperty(notes = "总费用/医保总费用")
    @JSONField(name = "医保总费用")
    private BigDecimal insuranceTotalFee;

    @ApiModelProperty(notes = "流水号/缴费流水号")
    @JSONField(name = "流水号")
    private String paySerialNumber;

    @ApiModelProperty(notes = "费用归属时间")
    @JSONField(name = "费用归属时间")
    private String costAscriptionTime;

    /*机构编码/机构ID 叫法不一样，数据其实一样*/
    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "医保入参")
    @JSONField(name = "医保入参")
    private String medicalInsuranceInputParam;

    @ApiModelProperty(notes = "医保出参")
    @JSONField(name = "医保出参")
    private String medicalInsuranceOutParam;

    @ApiModelProperty(notes = "操作人ID/操作员ID")
    @JSONField(name = "操作人ID")
    private String userId;

    @ApiModelProperty(notes = "操作人姓名/操作员")
    @JSONField(name = "操作人姓名")
    private String userName;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "备注")
    private String remark;

    @ApiModelProperty(notes = "打印信息,[{'个人账户支付':'3728.86','其他账户支付':'0.00','统筹支付':'0.00','报账合计':'3728.86','个人支付':'0.00'}]")
    @JSONField(name = "打印信息")
    private String printInfo;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("病人来源", this.getPatientFromType());
        inputJson.put("业务ID", this.getBusinessId());
        inputJson.put("姓名", this.getPatientName());
        inputJson.put("身份证", this.getIdCardNo());
        inputJson.put("医保卡号", this.getInsuranceNo());
        inputJson.put("医疗类别", this.getMedicalCategory());
        inputJson.put("人员类别", this.getPersonnelCategory());
        inputJson.put("待遇类别", this.getTreatmentCategory());
        inputJson.put("特殊待遇类别", this.getSpecialTreatmentCategory());
        inputJson.put("患者参保地名称", this.getPatientEnrollmentName());
        inputJson.put("患者参保地编码", this.getPatientEnrollmentCode());
        inputJson.put("清算方式名称", this.getLiquidationMethodName());
        inputJson.put("清算方式编码", this.getLiquidationMethodCode());
        inputJson.put("医保机构名称", this.getMedicalInsuranceAgencyName());
        inputJson.put("医保机构编码", this.getLiquidationMethodCode());
        inputJson.put("其他信息", this.getOtherInfo());
        inputJson.put("比例取法", this.getProportionalMethod());
        inputJson.put("疾病代码", this.getDiseaseCode());
        inputJson.put("打印信息", this.getPrintInfo());
        inputJson.put("备注", this.getRemark());
        inputJson.put("操作人姓名", this.getUserName());
        inputJson.put("操作人ID", this.getUserId());
        inputJson.put("医保出参", this.getMedicalInsuranceOutParam());
        inputJson.put("医保入参", this.getMedicalInsuranceInputParam());
        inputJson.put("机构编码", this.getOrganizationCode());
        inputJson.put("费用归属时间", this.getCostAscriptionTime());
        inputJson.put("流水号", this.getPaySerialNumber());
        inputJson.put("医保总费用", this.getInsuranceTotalFee());
        return inputJson.toJSONString();
    }
}
