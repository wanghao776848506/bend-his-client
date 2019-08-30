package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 医生
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "医生")
public class DoctorDto extends AbstractBaseEntity{

    /*request params*/
    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "科室编码或科室ID")
    @JSONField(name = "科室ID")
    private String sectionId;

    /*response data*/
    @ApiModelProperty(notes = "医生ID")
    @JSONField(name = "医生ID")
    private String doctorId;

    @ApiModelProperty(notes = "医生名称")
    @JSONField(name = "医生名称")
    private String doctorName;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("科室ID", this.getSectionId());
        inputJson.put("机构编码", this.getOrganizationCode());
        return inputJson.toJSONString();
    }

}
