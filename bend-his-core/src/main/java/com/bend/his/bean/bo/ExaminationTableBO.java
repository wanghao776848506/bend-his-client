package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ExaminationTableBO - 体检主表", description = "体检主表")
public class ExaminationTableBO {
    @ApiModelProperty(notes = "ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "居民ID/个人ID")
    @JSONField(name = "PersonID")
    private String personId;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "症状")
    @JSONField(name = "Symptom")
    private String symptom;

    @ApiModelProperty(notes = "健康评价是否异常：1 体检无异常 2 有异常")
    @JSONField(name = "Assessment")
    private String assessment;

    @ApiModelProperty(notes = "健康评价异常情况 内容逗号隔开")
    @JSONField(name = "AssessmentAbnormal")
    private String assessmentAbnormal;

    @ApiModelProperty(notes = "健康指导（所选数字2的次幂和？）：1 纳入慢性病患者健康管理 2 建议复查 3 建议转诊")
    @JSONField(name = "Guidance")
    private String guidance;

    @ApiModelProperty(notes = "危险因素控制integer（所选数字2的次幂和？） 1 戒烟 2 健康饮酒 3 饮食 4 锻炼 5 减体重 6 建议接种疫苗 7 其他")
    @JSONField(name = "RiskCrtl")
    private String riskCrtl;

    @ApiModelProperty(notes = "危险因素控制减重目标KG")
    @JSONField(name = "RiskCrtlWeight")
    private String riskCrtlWeight;

    @ApiModelProperty(notes = "危险因素控制建议疫苗")
    @JSONField(name = "RiskCrtlVaccine")
    private String riskCrtlVaccine;

    @ApiModelProperty(notes = "危险因素控制其他")
    @JSONField(name = "RiskCrtlOther")
    private String riskCrtlOther;

    @ApiModelProperty(notes = "健康摘要")
    @JSONField(name = "HealthSummary")
    private String healthSummary;

    @ApiModelProperty(notes = "责任医生ID")
    @JSONField(name = "DoctorID")
    private String doctorID;

    @ApiModelProperty(notes = "责任医生名")
    @JSONField(name = "DoctorName")
    private String doctorName;

    @ApiModelProperty(notes = "操作用户ID")
    @JSONField(name = "UserID")
    private String userID;

    @ApiModelProperty(notes = "操作用户名")
    @JSONField(name = "UserName")
    private String userName;

    @ApiModelProperty(notes = "是否完善")
    @JSONField(name = "IsStandard")
    private String isStandard;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "Remark")
    private String remark;

}
