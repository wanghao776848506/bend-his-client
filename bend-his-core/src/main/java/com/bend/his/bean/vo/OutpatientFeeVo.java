package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门诊费用明细查询
 */
@Data
@ApiModel(value = "OutpatientFeeVo - 门诊费用明细", description = "门诊费用明细")
public class OutpatientFeeVo extends BaseEntity {

    @ApiModelProperty(notes = "业务ID/门诊ID")
    @JSONField(name = "业务ID")
    private String businessId;

    @ApiModelProperty(notes = "门诊号")
    @JSONField(name = "门诊号")
    private String outpatientNumber;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("业务ID", this.getBusinessId());
        inputJson.put("门诊号", this.getOutpatientNumber());
        return inputJson.toJSONString();
    }
}
