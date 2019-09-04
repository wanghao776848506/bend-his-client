package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 诊疗结算：住院结算和门诊结算
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HospitalizationSettlementDto - 诊疗结算",description = "诊疗结算：住院结算和门诊结算")
public class HospitalizationSettlementDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "业务ID:住院ID")
    @JSONField(name = "业务ID")
    private String businessId;

    @ApiModelProperty(notes = "住院号")
    @JSONField(name = "住院号")
    private String hospitalizationNo;

    /*response data*/
    @ApiModelProperty(notes = "姓名:病人姓名")
    @JSONField(name = "病人姓名")
    private String patientName;

    @ApiModelProperty(notes = "清单开始时间")
    @JSONField(name = "清单开始时间")
    private String billBeginTime;

    @ApiModelProperty(notes = "清单截止时间")
    @JSONField(name = "清单截止时间")
    private String billEndTime;

    @ApiModelProperty(notes = "合计费用")
    @JSONField(name = "合计费用")
    private BigDecimal totalCost;

    @ApiModelProperty(notes = "操作员")
    @JSONField(name = "操作员")
    private String operator;

    @ApiModelProperty(notes = "结算类型")
    @JSONField(name = "结算类型")
    private String settlementType;


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("业务ID", this.getBusinessId());
        inputJson.put("住院号", this.getHospitalizationNo());
        return inputJson.toJSONString();
    }

}
