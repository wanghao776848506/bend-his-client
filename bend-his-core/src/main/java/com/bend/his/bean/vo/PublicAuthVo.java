package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;


@Getter
@Setter
@ApiModel(value = "PublicAuthVo - 公卫登录认证数据", description = "公卫登录认证数据")
public class PublicAuthVo extends BaseEntity {

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

    @ApiModelProperty(hidden = true)
    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("UserName", this.getUserName());
        inputJson.put("ProductCode", this.getProductCode());
        inputJson.put("Password", this.getPassword());
        return inputJson.toJSONString();
    }
}