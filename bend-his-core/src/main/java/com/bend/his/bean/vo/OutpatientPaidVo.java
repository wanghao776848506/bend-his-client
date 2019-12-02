package com.bend.his.bean.vo;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 门诊已缴费列表
 */
@Getter
@Setter
@ApiModel(value = "OutpatientPaidVo - 门诊已缴费列表 ", description = "门诊已缴费列表")
public class OutpatientPaidVo extends BaseEntity {
    /*门诊ID在其他接口里面也就是业务ID*/
    @ApiModelProperty(notes = "业务ID/门诊ID")
    @JSONField(name = "门诊ID")
    private String businessId;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("门诊ID", this.getBusinessId());
        return inputJson.toJSONString();
    }

}
