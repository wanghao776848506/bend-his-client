package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 门诊病人
 */
@Data
@ApiModel(value = "OutpatientDto - 门诊病人", description = "门诊病人")
public class OutpatientDto extends AbstractBaseEntity {

    /*response data*/
    @ApiModelProperty(notes = "姓名")
    @JSONField(name = "姓名")
    private String patientName;

    @ApiModelProperty(notes = "身份证号码")
    @JSONField(name = "身份证号码")
    private String idCardNo;

    @ApiModelProperty(notes = "性别")
    @JSONField(name = "性别")
    private String patientSex;

    @ApiModelProperty(notes = "业务ID/门诊ID")
    @JSONField(name = "业务ID")
    private String businessId;

    @ApiModelProperty(notes = "门诊号")
    @JSONField(name = "门诊号")
    private String outpatientNumber;

    @ApiModelProperty(notes = "就诊日期")
    @JSONField(name = "就诊日期")
    private String visitDate;

    @ApiModelProperty(notes = "科室编码或科室ID")
    @JSONField(name = "科室编码")
    private String departmentId;

    @ApiModelProperty(notes = "科室名称/科室")
    @JSONField(name = "科室")
    private String departmentName;

    @ApiModelProperty(notes = "诊断医生")
    @JSONField(name = "诊断医生")
    private String diagnosticDoctor;

    @ApiModelProperty(notes = "诊断疾病编码")
    @JSONField(name = "诊断疾病编码")
    private String diagnosticDiseaseCode;

    @ApiModelProperty(notes = "诊断疾病名称")
    @JSONField(name = "诊断疾病名称")
    private String diagnosticDiseaseName;

    @ApiModelProperty(notes = "主要病情描述")
    @JSONField(name = "主要病情描述")
    private String diseaseDesc;

    @ApiModelProperty(notes = "经办人")
    @JSONField(name = "经办人")
    private String operator;

    @ApiModelProperty(notes = "就诊总费用")
    @JSONField(name = "就诊总费用")
    private BigDecimal medicalTreatmentTotalCost;

    @ApiModelProperty(notes = "接诊状态[0未截至、1接诊中、2已接诊]")
    @JSONField(name = "接诊状态")
    private Integer receptionStatus;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "备注")
    private String remark;

}
