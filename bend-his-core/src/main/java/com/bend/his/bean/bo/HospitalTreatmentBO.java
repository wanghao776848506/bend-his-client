package com.bend.his.bean.bo;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HospitalTreatmentBO - 体检住院治疗情况",description = "体检住院治疗情况")
public class HospitalTreatmentBO {

    @ApiModelProperty(notes = "ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "随访ID")
    @JSONField(name = "DataMtID")
    private String dataMtID;

    @ApiModelProperty(notes = "历史类型:1 住院史 2 家庭病床史")
    @JSONField(name = "HistoryType")
    private String historyType;

    @ApiModelProperty(notes = "入院/建床日期(历史类型为1则为入院日期，为2则建床日期)")
    @JSONField(name = "InDate")
    private String inDate;

    @ApiModelProperty(notes = "出院/撤床日期(历史类型为1则为出院日期，为2则撤床日期)")
    @JSONField(name = "OutDate")
    private String outDate;

    @ApiModelProperty(notes = "原因")
    @JSONField(name = "Reason")
    private String reason;

    @ApiModelProperty(notes = "医疗机构名称")
    @JSONField(name = "OrgName")
    private String orgName;

    @ApiModelProperty(notes = "档案号")
    @JSONField(name = "MedicalRecordNumber")
    private String medicalRecordNumber;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;

}
