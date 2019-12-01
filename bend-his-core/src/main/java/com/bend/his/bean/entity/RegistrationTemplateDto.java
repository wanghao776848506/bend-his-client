package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 挂号模板
 */
@Getter
@Setter
@ApiModel(value = "RegistrationTemplateDto - 医院挂号模板", description = "医院挂号模板")
public class RegistrationTemplateDto {

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    /*response data*/
    @ApiModelProperty(notes = "模板ID")
    @JSONField(name = "模板ID")
    private String templateId;

    @ApiModelProperty(notes = "模板名称")
    @JSONField(name = "模板名称")
    private String templateName;

    @ApiModelProperty(notes = "挂号金额")
    @JSONField(name = "挂号金额")
    private String registrationAmount;

}
