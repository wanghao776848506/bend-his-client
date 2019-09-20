package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PublicAuthDto - 公卫登录认证数据", description = "公卫登录认证数据")
public class PublicAuthDto extends AbstractBaseEntity {

    /*params*/
    @ApiModelProperty(notes = "账号名/用户名/职员姓名")
    @JSONField(name = "UserName")
    private String userName;

    @ApiModelProperty(notes = "密码")
    @JSONField(name = "Password")
    private String password;

    @ApiModelProperty(notes = "产品验证码")
    @JSONField(name = "ProductCode")
    private String productCode;

    /*response data*/
    @ApiModelProperty(notes = "职员的PersonID")
    @JSONField(name = "UserId")
    private String userId;

    @ApiModelProperty(notes = "机构的名称")
    @JSONField(name = "OrgName")
    private String orgName;

    @ApiModelProperty(notes = "机构ID")
    @JSONField(name = "OrgId")
    private String orgId;

    @ApiModelProperty(notes = "机构的类型")
    @JSONField(name = "OrgType")
    private String orgType;

    @ApiModelProperty(notes = "管辖机构列表")
    @JSONField(name = "RegionCodeList")
    private List<String> regionCodeList;

/*    @ApiModelProperty(notes = "账号名/用户名/职员姓名")
    @JSONField(name = "UserName")
    private String userName;

    @ApiModelProperty(notes = "产品验证码")
    @JSONField(name = "ProductCode")
    private String productCode;*/


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("UserName", this.getUserName());
        inputJson.put("ProductCode", this.getProductCode());
        inputJson.put("Password", this.getPassword());
        return inputJson.toJSONString();
    }
}
