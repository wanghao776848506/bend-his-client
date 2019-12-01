package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 科室
 */
@Getter
@Setter
@ApiModel(value = "HospitalDepartmentDto - 医院科室", description = "医院科室")
public class HospitalDepartmentVo extends BaseEntity {

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "模板ID")
    @JSONField(name = "模板ID")
    private String templateId;

    @ApiModelProperty(notes = "科室编码/科室ID,查询参数")
    private String departmentId;

    @ApiModelProperty(notes = "科室名称,查询参数")
    private String departmentName;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("模板ID", this.getTemplateId());
        inputJson.put("机构编码", this.getOrganizationCode());
        return inputJson.toJSONString();
    }

}
