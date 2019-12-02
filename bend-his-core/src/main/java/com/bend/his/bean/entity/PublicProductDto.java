package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 公卫产品注册信息
 */
@Getter
@Setter
@ApiModel(value = "PublicProductDto - 公卫产品注册信息", description = "公卫产品注册信息")
public class PublicProductDto {
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

}
