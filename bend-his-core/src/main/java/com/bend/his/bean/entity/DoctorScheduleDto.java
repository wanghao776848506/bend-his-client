package com.bend.his.bean.entity;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 机构、人员的排班信息 -- 主要是医生排班表
 */
@Getter
@Setter
@ApiModel(value = "DoctorScheduleDto - 医生排班表", description = "医生排班表")
public class DoctorScheduleDto {

    @ApiModelProperty(notes = "医生ID")
    @JSONField(name = "医生ID")
    private String doctorId;

    @ApiModelProperty(notes = "医生名称")
    @JSONField(name = "医生名称")
    private String doctorName;

    @ApiModelProperty(notes = "排班日期")
    @JSONField(name = "排班日期")
    private String scheduleDate;

    @ApiModelProperty(notes = "班次[上午、下午]")
    @JSONField(name = "班次")
    private String scheduleShift;

    @ApiModelProperty(notes = "限制挂号数量")
    @JSONField(name = "限制挂号数量")
    private Integer registerLimitNum;

    @ApiModelProperty(notes = "已挂号数量")
    @JSONField(name = "已挂号数量")
    private Integer registeredNum;
}
