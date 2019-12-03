package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 医生
 */
@Data
@ApiModel(value = "DoctorDto - 医生", description = "医生")
public class DoctorDto extends AbstractBaseEntity{

    /*response data*/
    @ApiModelProperty(notes = "医生ID")
    @JSONField(name = "医生ID")
    private String doctorId;

    @ApiModelProperty(notes = "医生名称")
    @JSONField(name = "医生名称")
    private String doctorName;

    @ApiModelProperty(notes = "科室名称")
    @JSONField(name = "科室名称")
    private String departmentName;

    @ApiModelProperty(notes = "科室编码或科室ID")
    @JSONField(name = "科室ID")
    private String departmentId;

}
