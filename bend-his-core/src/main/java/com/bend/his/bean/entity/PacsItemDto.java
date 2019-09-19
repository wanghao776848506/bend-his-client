package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PACS检查项目
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PacsItemDto - PACS检查项目", description = "PACS检查项目")
public class PacsItemDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "检查包名称")
    @JSONField(name = "检查包名称")
    private String checkPackageName;

    @ApiModelProperty(notes = "检查包编码")
    @JSONField(name = "检查包编码")
    private String checkPackageCode;

    @ApiModelProperty(notes = "检查包ID")
    @JSONField(name = "检查包ID")
    private String checkPackageId;

    @ApiModelProperty(notes = "检查项目名称")
    @JSONField(name = "检查项目名称")
    private String checkItemName;

    @ApiModelProperty(notes = "检查项目编码")
    @JSONField(name = "检查项目编码")
    private String checkItemCode;

    @ApiModelProperty(notes = "部位")
    @JSONField(name = "部位")
    private String checkPart;

    @ApiModelProperty(notes = "医院编码")
    @JSONField(name = "医院编码")
    private String code;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("机构编码", this.getOrganizationCode());
        return inputJson.toJSONString();
    }


}
