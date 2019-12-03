package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 取消挂号
 */
@Data
@ApiModel(value = "RegistrationCancelVo - 取消挂号", description = "取消挂号")
public class RegistrationCancelVo extends BaseEntity {

    @ApiModelProperty(notes = "挂号ID")
    @JSONField(name = "挂号ID")
    private String registrationId;

    @ApiModelProperty(notes = "收费人员ID")
    @JSONField(name = "收费人员ID")
    private String userId;

    @ApiModelProperty(notes = "厂商编号或厂商唯一标识")
    @JSONField(name = "厂商唯一标识")
    private String manufacturerNumber;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("收费人员ID", this.getUserId());
        inputJson.put("厂商唯一标识", this.getManufacturerNumber());
        inputJson.put("挂号ID", this.getRegistrationId());
        return inputJson.toJSONString();
    }
}
