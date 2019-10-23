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
@ApiModel(value = "TCMConstitutionBO - 体检中医体质辨识", description = "体检中医体质辨识")
public class TCMConstitutionBO {

    @ApiModelProperty(notes = "")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "平和质：1是 2倾向是")
    @JSONField(name = "ModerateQuality")
    private String moderateQuality;

    @ApiModelProperty(notes = "气虚质：1是 2倾向是")
    @JSONField(name = "QualityDeficiency")
    private String qualityDeficiency;

    @ApiModelProperty(notes = "阳虚质：1是 2倾向是")
    @JSONField(name = "YangQuality")
    private String yangQuality;

    @ApiModelProperty(notes = "阴虚质：1是 2倾向是")
    @JSONField(name = "YinQuality")
    private String yinQuality;

    @ApiModelProperty(notes = "痰湿质：1是 2倾向是")
    @JSONField(name = "Phlegm")
    private String phlegm;

    @ApiModelProperty(notes = "湿热质：1是 2倾向是")
    @JSONField(name = "DampHeat")
    private String dampHeat;

    @ApiModelProperty(notes = "血瘀质：1是 2倾向是")
    @JSONField(name = "BloodQuality")
    private String bloodQuality;

    @ApiModelProperty(notes = "气郁质：1是 2倾向是")
    @JSONField(name = "QiQuality")
    private String qiQuality;

    @ApiModelProperty(notes = "特秉质：1是 2倾向是")
    @JSONField(name = "TeBingQuality")
    private String teBingQuality;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;
}
