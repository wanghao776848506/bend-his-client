package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 费用账单/清单
 */
@Getter
@Setter
@ApiModel(value = "ExpenseBillVo - 费用清单/账单 ", description = "费用清单/账单")
public class ExpenseBillVo extends BaseEntity {

    @ApiModelProperty(notes = "收费记录ID")
    @JSONField(name = "收费记录ID")
    private String chargeRecordId;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("收费记录ID", this.getChargeRecordId());
        return inputJson.toJSONString();
    }
}
