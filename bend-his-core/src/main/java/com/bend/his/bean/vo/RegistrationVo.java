package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.PayAccountBO;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 门诊挂号
 */
@Data
@ApiModel(value = "RegistrationVo - 门诊挂号", description = "门诊挂号")
public class RegistrationVo extends BaseEntity {

    @ApiModelProperty(notes = "病人姓名")
    @JSONField(name = "病人姓名")
    private String patientName;

    @ApiModelProperty(notes = "病人性别")
    @JSONField(name = "病人性别")
    private String patientSex;

    @ApiModelProperty(notes = "身份证号码")
    @JSONField(name = "身份证号码")
    private String idCardNo;

    @ApiModelProperty(notes = "收费人员ID")
    @JSONField(name = "收费人员ID")
    private String userId;

    @ApiModelProperty(notes = "金额")
    @JSONField(name = "金额")
    private String fee;

    @ApiModelProperty(notes = "费用类型ID")
    @JSONField(name = "费用类型ID")
    private String costTypeId;

    @ApiModelProperty(notes = "模板ID")
    @JSONField(name = "模板ID")
    private String templateId;

    @ApiModelProperty(notes = "科室编码或科室ID")
    @JSONField(name = "科室ID")
    private String departmentId;

    @ApiModelProperty(notes = "医生ID")
    @JSONField(name = "医生ID")
    private String doctorId;

    @ApiModelProperty(notes = "缴费方式列表,格式：[{'PaymentID':'支付方式ID','OrgAccID':'账户ID'，'Fee','金额'}]")
    private List<PayAccountBO> paymentList;

    @ApiModelProperty(notes = "缴费方式列表,格式：[{'PaymentID':'支付方式ID','OrgAccID':'账户ID'，'Fee','金额'}]")
    @JSONField(name = "缴费方式列表")
    private String paymentListStr;

    @ApiModelProperty(notes = "挂号日期[YYYY-MM-DD，注：为空默认为当前时间]")
    @JSONField(name = "挂号日期")
    private String createDate; //YYYY-MM-DD，注：为空默认为当前时间

    @ApiModelProperty(notes = "厂商编号或厂商唯一标识")
    @JSONField(name = "厂商唯一标识")
    private String manufacturerNumber;

    @ApiModelProperty(notes = "缴费流水号")
    @JSONField(name = "缴费流水号")
    private String paySerialNumber;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("病人姓名", this.getPatientName());
        inputJson.put("病人性别", this.getPatientSex());
        inputJson.put("身份证号码", this.getIdCardNo());
        inputJson.put("收费人员ID", this.getUserId());
        inputJson.put("金额", this.getFee());
        inputJson.put("费用类型ID", this.getCostTypeId());
        inputJson.put("模板ID", this.getTemplateId());
        inputJson.put("科室ID", this.getDepartmentId());
        inputJson.put("医生ID", this.getDoctorId());
        inputJson.put("缴费方式列表", this.getPaymentListStr());
        inputJson.put("挂号日期", this.getCreateDate());
        inputJson.put("厂商唯一标识", this.getManufacturerNumber());
        inputJson.put("缴费流水号", this.getPaySerialNumber());
        return inputJson.toJSONString();
    }
}
