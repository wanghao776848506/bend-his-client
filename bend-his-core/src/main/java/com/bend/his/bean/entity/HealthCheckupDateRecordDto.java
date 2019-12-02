package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 个人健康体检记录日期列表
 */
@Getter
@Setter
@ApiModel(value = "HealthCheckupDateRecordDto - 个人健康体检记录日期列表", description = "个人健康体检记录日期列表")
public class HealthCheckupDateRecordDto {

    @ApiModelProperty(notes = "随访ID")
    @JSONField(name = "MtID")
    private String mtId;
    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

}
