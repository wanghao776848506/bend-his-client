package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 住院预交费
 */
@Getter
@Setter
@ApiModel(value = "PrepaymentRecordVo - 住院预交费记录", description = "住院预交费记录")
public class PrepaymentRecordVo extends BaseEntity {

    @ApiModelProperty(notes = "住院ID/业务ID")
    @JSONField(name = "住院ID")
    private String hospitalizationId;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("住院ID", this.getHospitalizationId());
        return inputJson.toJSONString();
    }
}
