package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 科室
 */
@Getter
@Setter
@ApiModel(value = "HospitalDepartmentDto - 医院科室", description = "医院科室")
public class HospitalDepartmentDto {

    /*response data*/
    @ApiModelProperty(notes = "科室编码或科室ID")
    @JSONField(name = "科室ID")
    private String departmentId;

    @ApiModelProperty(notes = "科室名称")
    @JSONField(name = "科室名称")
    private String departmentName;

    @ApiModelProperty(notes = "目录类型[0科室、1医生、2病区、3床位]", hidden = true)
    private String directoryType;

    @ApiModelProperty(notes = "挂号模板列表", hidden = true)
    private List<RegistrationTemplateDto> registrationTemplateList;

    @ApiModelProperty(notes = "挂号模板", hidden = true)
    private RegistrationTemplateDto registrationTemplateDto;
}
