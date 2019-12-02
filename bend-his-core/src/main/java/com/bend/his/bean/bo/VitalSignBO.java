package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ApiModel(value = "VitalSignBO - 体征", description = "体征")
public class VitalSignBO {

    @ApiModelProperty(notes = "")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "体温")
    @JSONField(name = "BodyTemperature")
    private String bodyTemperature;

    @ApiModelProperty(notes = "脉率")
    @JSONField(name = "PulseRate")
    private String pulseRate;

    @ApiModelProperty(notes = "心率")
    @JSONField(name = "HeartRate")
    private String heartRate;

    @ApiModelProperty(notes = "呼吸频率")
    @JSONField(name = "RespiratoryRate")
    private String respiratoryRate;

    @ApiModelProperty(notes = "左侧收缩压")
    @JSONField(name = "LeftSbp")
    private String leftSbp;

    @ApiModelProperty(notes = "左侧舒张压")
    @JSONField(name = "LeftDbp")
    private String leftDbp;

    @ApiModelProperty(notes = "右侧收缩压")
    @JSONField(name = "RightSbp")
    private String rightSbp;

    @ApiModelProperty(notes = "右侧舒张压")
    @JSONField(name = "RightDbp")
    private String rightDbp;

    @ApiModelProperty(notes = "身高cm")
    @JSONField(name = "Height")
    private String height;

    @ApiModelProperty(notes = "体重kg")
    @JSONField(name = "Weight")
    private String weight;

    @ApiModelProperty(notes = "腰围cm")
    @JSONField(name = "Waistline")
    private String waistline;

    @ApiModelProperty(notes = "臀围cm")
    @JSONField(name = "Hip")
    private String hip;

    @ApiModelProperty(notes = "体质指数")
    @JSONField(name = "Bmi")
    private String bmi;

    @ApiModelProperty(notes = "腰臀比")
    @JSONField(name = "Whr")
    private String whr;

    @ApiModelProperty(notes = "足背动脉搏动")
    @JSONField(name = "DorsalisPedisArteryPulse")
    private String dorsalisPedisArteryPulse;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;
}
