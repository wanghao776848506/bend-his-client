package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 科室
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HospitalDepartmentDto - 医院科室", description = "医院科室")
public class HospitalDepartmentDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "模板ID")
    @JSONField(name = "模板ID")
    private String templateId;

    /*response data*/
    @ApiModelProperty(notes = "科室编码或科室ID")
    @JSONField(name = "科室ID")
    private String departmentId;

    @ApiModelProperty(notes = "科室名称")
    @JSONField(name = "科室名称")
    private String departmentName;

    @ApiModelProperty(notes = "科室下挂号模板列表")
    private List<RegistrationTemplateDto> registrationTemplateList;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("模板ID", this.getTemplateId());
        inputJson.put("机构编码", this.getOrganizationCode());
        return inputJson.toJSONString();
    }

}
