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
@ApiModel(value = "DrugUseBO - 体检主要用药情况", description = "体检主要用药情况")
public class DrugUseBO {

    @ApiModelProperty(notes = "ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "随访ID")
    @JSONField(name = "DataMtID")
    private String dataMtID;

    @ApiModelProperty(notes = "药物名称")
    @JSONField(name = "DrugName")
    private String drugName;

    @ApiModelProperty(notes = "用法")
    @JSONField(name = "Usage")
    private String usage;

    @ApiModelProperty(notes = "用量")
    @JSONField(name = "Amount")
    private String amount;

    @ApiModelProperty(notes = "用药时长")
    @JSONField(name = "MedicationTime")
    private String medicationTime;

    @ApiModelProperty(notes = "用药时长单位：1年 2月 3天")
    @JSONField(name = "MedicationUnit")
    private String medicationUnit;

    @ApiModelProperty(notes = "服药依从性：1规律 2间断 3不服药")
    @JSONField(name = "MedicationCompliance")
    private String medicationCompliance;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;

}
