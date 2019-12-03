package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 公卫产品注册信息
 */
@Data
@ApiModel(value = "PublicProductVo - 公卫产品注册信息", description = "公卫产品注册信息")
public class PublicProductVo {


    @ApiModelProperty(notes = "账号名/用户名/职员姓名 -- 是基卫系统登录用户姓名")
    @JSONField(name = "UserName")
    private String userName;

    @ApiModelProperty(notes = "密码 -- 是基卫系统登录用户密码")
    @JSONField(name = "Password")
    private String password;

    @ApiModelProperty(notes = "厂商代码(联系信息中心获取)")
    @JSONField(name = "CompanyCode")
    private String companyCode;

    @ApiModelProperty(notes = "产品类型: 02，健康小屋；03，健康一体机；04,APP应用；05，其他")
    @JSONField(name = "ProductType")
    private String productType;

    @ApiModelProperty(notes = "产品名称 - 终端产品名称")
    @JSONField(name = "ProductName")
    private String productName;

    @ApiModelProperty(notes = "终端唯一识别号")
    @JSONField(name = "ExtInfo")
    private String extInfo;


    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("CompanyCode", this.getCompanyCode());
        inputJson.put("ProductName", this.getProductName());
        inputJson.put("ProductType", this.getProductType());
        inputJson.put("ExtInfo", this.getExtInfo());
        inputJson.put("UserName", this.getUserName());
        inputJson.put("Password", this.getPassword());
        return inputJson.toJSONString();
    }


}
