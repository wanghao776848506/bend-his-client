package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公卫产品注册信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PublicProductDto - 公卫产品注册信息", description = "公卫产品注册信息")
public class PublicProductDto extends AbstractBaseEntity {


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

    /*response data*/

    @ApiModelProperty(notes = "产品验证码 - 终端产品授权码")
    @JSONField(name = "ProductCode")
    private String productCode;

    /*response data*/
    @ApiModelProperty(notes = "机构名称 - 终端所在机构")
    @JSONField(name = "OrgName")
    private String orgName;

    @ApiModelProperty(notes = "机构ID - 终端所在机构ID")
    @JSONField(name = "OrgId")
    private String orgId;

    @ApiModelProperty(notes = "机构类型-终端所在机构类型")
    @JSONField(name = "OrgType")
    private String orgType;

    /**
     * CompanyCode	是	string	厂商代码(联系信息中心获取)
     * ProductName	是	string	产品名称
     * ProductType	是	string
     * ExtInfo	是	string	终端唯一识别号
     * UserName	是	string	基卫系统登录用户姓名
     * Password	是	string	基卫系统登录用户密码
     */

    @Override
    public String createJSONObject() {
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
