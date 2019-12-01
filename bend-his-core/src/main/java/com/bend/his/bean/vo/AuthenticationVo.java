package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
@Getter
@Setter
@ApiModel(value = "AuthenticationVo - 登录认证数据", description = "登录认证数据")
public class AuthenticationVo extends BaseEntity {

    /*params*/
    @ApiModelProperty(notes = "用户名")
    @JSONField(name = "用户名")
    private String memberName;

    @ApiModelProperty(notes = "密码")
    @JSONField(name = "密码")
    private String password;

    @ApiModelProperty(notes = "厂商编号")
    @JSONField(name = "厂商编号")
    private String manufacturerNumber;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("用户名", this.getMemberName());
        inputJson.put("密码", this.getPassword());
        inputJson.put("厂商编号", this.getManufacturerNumber());
        return inputJson.toJSONString();
    }
}
