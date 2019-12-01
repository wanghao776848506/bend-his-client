package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * 病人挂号-挂号费用类型
 */
@Getter
@Setter
@ApiModel(value = "RegistrationFeeTypeVo - 挂号费用类型", description = "挂号费用类型")
public class RegistrationFeeTypeVo extends BaseEntity {
    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        return inputJson.toJSONString();
    }
}
