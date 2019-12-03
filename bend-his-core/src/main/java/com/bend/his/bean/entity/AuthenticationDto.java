package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户登录信息:
 * [
 * {
 * "机构编码":"51072600000000000000000513435964",
 * "管辖区划":["510726103"],
 * "机构名称":"北川羌族自治县永安镇中心卫生院",
 * "验证码":"44200F7B173B00E136C04106FB29AA4A",
 * "职员ID":"E075AC49FCE443778F897CF839F3B924",
 * "机构类型":"2",
 * "职员姓名":"李茜"
 * }
 * ]
 */
@Data
@ApiModel(value = "AuthenticationDto - 登录认证数据", description = "登录认证数据")
public class AuthenticationDto extends AbstractBaseEntity{


    /*response data*/
    @ApiModelProperty(notes = "验证码/授权码")
    @JSONField(name = "验证码")
    protected String authCode;

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "管辖区划")
    @JSONField(name = "管辖区划")
    private List<String> regionCodeList;

    @ApiModelProperty(notes = "机构名称")
    @JSONField(name = "机构名称")
    private String hospitalName;

    @ApiModelProperty(notes = "职员ID")
    @JSONField(name = "职员ID")
    private String userId;

    @ApiModelProperty(notes = "机构类型")
    @JSONField(name = "机构类型")
    private String organizationType;

    @ApiModelProperty(notes = "职员姓名")
    @JSONField(name = "职员姓名")
    private String userName;
}
