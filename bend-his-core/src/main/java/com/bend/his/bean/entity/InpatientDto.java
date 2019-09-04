package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 住院病人
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "InpatientDto - 住院病人",description = "住院病人")
public class InpatientDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "身份证号码")
    @JSONField(name = "身份证号码")
    private String idCardNo;

    @ApiModelProperty(notes = "开始时间")
    @JSONField(name = "开始时间")
    private String beginTime;

    @ApiModelProperty(notes = "结束时间")
    @JSONField(name = "结束时间")
    private String endTime;

    @ApiModelProperty(notes = "状态[0表示清单不包含退药、退费等产生的负数记录；1表示清单包含退药、退费等产生的负数记录。（非必填项：默认为0 ）]")
    @JSONField(name = "状态")
    private Integer state;

    /*response data*/
    @ApiModelProperty(notes = "医院名称")
    @JSONField(name = "医院名称")
    private String hospitalName;

    @ApiModelProperty(notes = "入院日期")
    @JSONField(name = "入院日期")
    private String admissionDate;

    @ApiModelProperty(notes = "出院日期")
    @JSONField(name = "出院日期")
    private String leaveHospitalDate;

    @ApiModelProperty(notes = "住院号")
    @JSONField(name = "住院号")
    private String hospitalizationNo;

    @ApiModelProperty(notes = "业务ID")
    @JSONField(name = "业务ID")
    private String businessId;

    @ApiModelProperty(notes = "姓名")
    @JSONField(name = "姓名")
    private String patientName;

    @ApiModelProperty(notes = "性别")
    @JSONField(name = "性别")
    private String patientSex;

    @ApiModelProperty(notes = "出生日期")
    @JSONField(name = "出生日期")
    private String birthday;

    @ApiModelProperty(notes = "联系人姓名")
    @JSONField(name = "联系人姓名")
    private String contactName;

    @ApiModelProperty(notes = "联系电话")
    @JSONField(name = "联系电话")
    private String contactPhone;

    @ApiModelProperty(notes = "家庭地址")
    @JSONField(name = "家庭地址")
    private String familyAddress;

    @ApiModelProperty(notes = "入院科室")
    @JSONField(name = "入院科室")
    private String inSectionName;

    @ApiModelProperty(notes = "入院科室编码")
    @JSONField(name = "入院科室编码")
    private String inSectionId;

    @ApiModelProperty(notes = "入院诊断医生")
    @JSONField(name = "入院诊断医生")
    private String admissionDiagnosticDoctor;

    @ApiModelProperty(notes = "入院床位")
    @JSONField(name = "入院床位")
    private String admissionBed;

    @ApiModelProperty(notes = "入院主诊断")
    @JSONField(name = "入院主诊断")
    private String admissionMainDiagnosis;

    @ApiModelProperty(notes = "入院主诊断ICD10")
    @JSONField(name = "入院主诊断ICD10")
    private String admissionMainDiagnosisICD10;

    @ApiModelProperty(notes = "入院次诊断")
    @JSONField(name = "入院次诊断")
    private String admissionSecondaryDiagnosis;

    @ApiModelProperty(notes = "入院次诊断ICD10")
    @JSONField(name = "入院次诊断ICD10")
    private String admissionSecondaryDiagnosisICD10;

    @ApiModelProperty(notes = "入院病区")
    @JSONField(name = "入院病区")
    private String admissionWard;

    @ApiModelProperty(notes = "入院经办人")
    @JSONField(name = "入院经办人")
    private String admissionOperator;

    @ApiModelProperty(notes = "入院经办时间")
    @JSONField(name = "入院经办时间")
    private String admissionOperateTime;

    @ApiModelProperty(notes = "住院总费用")
    @JSONField(name = "住院总费用")
    private String hospitalizationTotalCost;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "备注")
    private String remark;

    @ApiModelProperty(notes = "出院科室名称")
    @JSONField(name = "出院科室名称")
    private String leaveSectionName;

    @ApiModelProperty(notes = "出院科室编码")
    @JSONField(name = "出院科室编码")
    private String leaveSectionId;

    @ApiModelProperty(notes = "出院病区")
    @JSONField(name = "出院病区")
    private String leaveHospitalWard;

    @ApiModelProperty(notes = "出院床位")
    @JSONField(name = "出院床位")
    private String leaveHospitalBed;

    @ApiModelProperty(notes = "出院主诊断")
    @JSONField(name = "出院主诊断")
    private String leaveHospitalMainDiagnosis;

    @ApiModelProperty(notes = "出院主诊断ICD10")
    @JSONField(name = "出院主诊断ICD10")
    private String leaveHospitalMainDiagnosisICD10;

    @ApiModelProperty(notes = "出院次诊断")
    @JSONField(name = "出院次诊断")
    private String leaveHospitalSecondaryDiagnosis;

    @ApiModelProperty(notes = "出院次诊断ICD10")
    @JSONField(name = "出院次诊断ICD10")
    private String leaveHospitalSecondaryDiagnosisICD10;

    @ApiModelProperty(notes = "在院状态[0在院无床、1在院有床、2出院未结算、3出院已结算、-1撤销入院]")
    @JSONField(name = "在院状态")
    private String inpatientHospitalState;

    @ApiModelProperty(notes = "入院诊断医生编码")
    @JSONField(name = "入院诊断医生编码")
    private String admissionDiagnosticDoctorId;

    @ApiModelProperty(notes = "入院床位编码")
    @JSONField(name = "入院床位编码")
    private String admissionBedId;

    @ApiModelProperty(notes = "入院病区编码")
    @JSONField(name = "入院病区编码")
    private String admissionWardId;

    @ApiModelProperty(notes = "出院床位编码")
    @JSONField(name = "出院床位编码")
    private String leaveHospitalBedId;

    @ApiModelProperty(notes = "出院病区编码")
    @JSONField(name = "出院病区编码")
    private String leaveHospitalWardId;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("身份证号码", this.getIdCardNo());
        inputJson.put("机构编码", this.getOrganizationCode());
        inputJson.put("开始时间", this.getBeginTime());
        inputJson.put("结束时间", this.getEndTime());
        inputJson.put("状态", this.getState());
        return inputJson.toJSONString();
    }
}
