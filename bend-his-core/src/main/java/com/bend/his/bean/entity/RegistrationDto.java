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
 * 门诊挂号
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RegistrationDto - 门诊挂号",description = "门诊挂号")
public class RegistrationDto extends AbstractBaseEntity {

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

    /*机构编码/机构ID 叫法不一样，数据其实一样*/
    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "开始时间/日期")
    @JSONField(name = "开始日期")
    private String beginTime;

    @ApiModelProperty(notes = "结束时间/日期")
    @JSONField(name = "结束日期")
    private String endTime;

    @ApiModelProperty(notes = "挂号ID")
    @JSONField(name = "挂号ID")
    private String registrationId;

    /*response data*/
    @ApiModelProperty(notes = "ID/挂号ID--返回值")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "挂号CODE")
    @JSONField(name = "CODE")
    private String registrationCode;

    @ApiModelProperty(notes = "医院名称")
    @JSONField(name = "医院名称")
    private String hospitalName;

    @ApiModelProperty(notes = "科室或科室名称")
    @JSONField(name = "科室")
    private String departmentName;

    @ApiModelProperty(notes = "医生或医生名称")
    @JSONField(name = "医生")
    private String doctorName;

    @ApiModelProperty(notes = "是否可退[0是1否]")
    @JSONField(name = "是否可退")
    private String refundable;

    @Override
    public String createJSONObject() {
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
        /*查询挂号记录*/
//        inputJson.put("验证码", this.getAuthCode());
//        inputJson.put("身份证号码", this.getIdCardNo());
        inputJson.put("挂号ID", this.getRegistrationId());
        inputJson.put("机构编码", this.getOrganizationCode());
        inputJson.put("开始日期", this.getBeginTime());
        inputJson.put("结束日期", this.getEndTime());
        return inputJson.toJSONString();
    }
}
