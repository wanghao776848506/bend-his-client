package com.bend.his.bean.entity;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 为家庭医生签约字典
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "CustomDictDto - 自定义字典数据", description = "自定义字典数据")
public class CustomDictDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "产品验证码")
    @JSONField(name = "ProductCode")
    private String productCode;

    @ApiModelProperty(notes = "字典类型-字典分类：SFamilyTags 人群标签 SFamilyItemDuty 成员职责 SFamilyServSign 服务包分类 SFamilyDOCTOR 团队类型 SFamilyChannel 签约渠道")
    @JSONField(name = "dictType")
    private String dictType;

    @ApiModelProperty(notes = "主键ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "字典分类")
    @JSONField(name = "Code")
    private String code;

    @ApiModelProperty(notes = "字典")
    @JSONField(name = "Name")
    private String name;

    @ApiModelProperty(notes = "字典拼音")
    @JSONField(name = "NamePy")
    private String namePy;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "Remark")
    private String remark;

    @ApiModelProperty(notes = "机构ID")
    @JSONField(name = "OrgId")
    private String orgId;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("DictType", this.getDictType());
        inputJson.put("ProductCode", this.getProductCode());
        return inputJson.toJSONString();
    }

}
