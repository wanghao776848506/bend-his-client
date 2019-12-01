package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 医生
 */
@Getter
@Setter
@ApiModel(value = "DoctorVo - 医生", description = "医生")
public class DoctorVo extends BaseEntity {

    /*request params*/
    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "科室编码或科室ID")
    @JSONField(name = "科室ID")
    private String departmentId;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("科室ID", this.getDepartmentId());
        inputJson.put("机构编码", this.getOrganizationCode());
        return inputJson.toJSONString();
    }

}
