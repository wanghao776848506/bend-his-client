package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 医院综合目录查询(科室、医生、病区、床位)
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ComprehensiveCatalogueDto - 医院综合目录",description = "医院综合目录查询")
public class ComprehensiveCatalogueDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "目录类型[0科室、1医生、2病区、3床位]")
    @JSONField(name = "目录类型")
    private String directoryType;

    @ApiModelProperty(notes = "目录名称")
    @JSONField(name = "目录名称")
    private String directoryName;


    /*response data*/
    @ApiModelProperty(notes = "目录编码")
    @JSONField(name = "目录编码")
    private String directoryCode;

    @ApiModelProperty(notes = "助记码")
    @JSONField(name = "助记码")
    private String mnemonicCode;

    @ApiModelProperty(notes = "目录类别名称")
    @JSONField(name = "目录类别名称")
    private String directoryCategoryName;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "备注")
    private String remark;

    @ApiModelProperty(notes = "病区")
    @JSONField(name = "病区")
    private String inpatientWard;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("目录类型", this.getDirectoryType());
        inputJson.put("目录名称", this.getDirectoryName());
        inputJson.put("机构编码", this.getOrganizationCode());
        return inputJson.toJSONString();
    }

}
