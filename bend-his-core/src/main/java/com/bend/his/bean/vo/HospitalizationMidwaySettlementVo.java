package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "HospitalizationMidwaySettlementVo - 中途结算记录查询", description = "中途结算记录查询")
public class HospitalizationMidwaySettlementVo extends BaseEntity {
    @ApiModelProperty(notes = "业务ID/住院ID")
    @JSONField(name = "业务ID")
    private String businessId;

    @ApiModelProperty(notes = "住院号")
    @JSONField(name = "住院号")
    private String hospitalizationNo;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("业务ID", this.getBusinessId());
        inputJson.put("住院号", this.getHospitalizationNo());
        return inputJson.toJSONString();
    }
}
