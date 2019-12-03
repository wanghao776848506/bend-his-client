package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门诊退费
 */
@Data
@ApiModel(value = "OutpatientRefundVo - 门诊退费 ", description = "门诊退费")
public class OutpatientRefundVo extends BaseEntity {

    @ApiModelProperty(notes = "收费记录ID/记录ID")
    @JSONField(name = "收费记录ID")
    private String chargeRecordId;

    @ApiModelProperty(notes = "虚拟收费人员ID")
    @JSONField(name = "虚拟收费人员ID")
    private String vmUserId;

    @ApiModelProperty(notes = "退费金额，主要用于验证费用是否相符")
    @JSONField(name = "退费金额")
    private String refundAmount;

    @ApiModelProperty(notes = "退费流水号")
    @JSONField(name = "退费流水号")
    private String refundSerialNumber;

    @ApiModelProperty(notes = "厂商编号或厂商唯一标识")
    @JSONField(name = "厂商唯一标识")
    private String manufacturerNumber;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("收费记录ID", this.getChargeRecordId());
        inputJson.put("虚拟收费人员ID", this.getVmUserId());
        inputJson.put("退费金额", this.getRefundAmount());
        inputJson.put("厂商唯一标识", this.getManufacturerNumber());
        inputJson.put("退费流水号", this.getRefundSerialNumber());
        return inputJson.toJSONString();
    }

}
