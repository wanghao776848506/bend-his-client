package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 中途结算记录查询
 */
@Data
@ApiModel(value = "HospitalizationMidwaySettlementDto - 中途结算记录查询", description = "中途结算记录查询")
public class HospitalizationMidwaySettlementDto extends AbstractBaseEntity {
    /*response data*/
    @ApiModelProperty(notes = "姓名/病人姓名")
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
}
