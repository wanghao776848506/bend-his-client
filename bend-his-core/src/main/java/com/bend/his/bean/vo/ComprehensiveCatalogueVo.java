package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 医院综合目录查询(科室、医生、病区、床位)
 */
@Getter
@Setter
@ApiModel(value = "ComprehensiveCatalogueVo - 医院综合目录", description = "医院综合目录查询")
public class ComprehensiveCatalogueVo extends BaseEntity {

    @ApiModelProperty(notes = "验证码/授权码")
    @JSONField(name = "验证码")
    protected String authCode;

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "目录类型[0科室、1医生、2病区、3床位]")
    @JSONField(name = "目录类型")
    private String directoryType;

    @ApiModelProperty(notes = "目录名称")
    @JSONField(name = "目录名称")
    private String directoryName;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("目录类型", this.getDirectoryType());
        inputJson.put("目录名称", this.getDirectoryName());
        inputJson.put("机构编码", this.getOrganizationCode());
        return inputJson.toJSONString();
    }

}
