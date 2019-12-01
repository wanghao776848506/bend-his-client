package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 挂号模板
 */
@Getter
@Setter
@ApiModel(value = "RegistrationTemplateVo - 医院挂号模板", description = "医院挂号模板")
public class RegistrationTemplateVo extends BaseEntity {

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "模板ID")
    @JSONField(name = "模板ID")
    private String templateId;


    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("机构编码", this.getOrganizationCode());
        inputJson.put("模板ID", this.getTemplateId());
        return inputJson.toJSONString();
    }
}
