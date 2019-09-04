package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机构信息:获取HIS系统中医疗机构的详细信息<br/>
 * 包含：乡镇卫生院和社区服务中心
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HospitalOrganizationDto - 医院机构信息",description = "医院机构信息")
public class HospitalOrganizationDto extends AbstractBaseEntity {
    /*request params*/
    @ApiModelProperty(notes = "医院名称")
    @JSONField(name = "医院名称")
    private String hospitalName;

    @ApiModelProperty(notes = "机构名称")
    @JSONField(name = "机构名称")
    private String organizationName;

    /*response data*/
    @ApiModelProperty(notes = "医院ID")
    @JSONField(name = "ID")
    private String hospitalId;

    @ApiModelProperty(notes = "医院地址")
    @JSONField(name = "医院地址")
    private String hospitalAddr;

    @ApiModelProperty(notes = "联系电话")
    @JSONField(name = "联系电话")
    private String contactPhone;

    @ApiModelProperty(notes = "邮政编码")
    @JSONField(name = "邮政编码")
    private String postalCode;

    @ApiModelProperty(notes = "联系人")
    @JSONField(name = "联系人")
    private String contactPerson;

    @ApiModelProperty(notes = "机构ID")
    @JSONField(name = "机构ID")
    private String organizationID;

    @ApiModelProperty(notes = "机构地址")
    @JSONField(name = "机构地址")
    private String organizationAddr;


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("医院名称", this.getHospitalName());
        inputJson.put("机构名称", this.getOrganizationName());
        return inputJson.toJSONString();
    }
}
