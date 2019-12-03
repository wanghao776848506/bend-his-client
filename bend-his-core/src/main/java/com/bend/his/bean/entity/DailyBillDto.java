package com.bend.his.bean.entity;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 每日账单/清单
 */
@Data
@ApiModel(value = "DailyBillDto - 每日清单(住院)", description = "每日清单(住院)")
public class DailyBillDto extends AbstractBaseEntity {


    @ApiModelProperty(notes = "每日清单ID")
    @JSONField(name = "ID")
    private String dailyBillId;

    @ApiModelProperty(notes = "时间/查询日期")
    @JSONField(name = "时间")
    private String dailyDay;

    @ApiModelProperty(notes = "项目名称[费用]")
    @JSONField(name = "项目名称")
    private String costItemName;

    @ApiModelProperty(notes = "数量")
    @JSONField(name = "数量")
    private Double quantity;

    @ApiModelProperty(notes = "金额/小计/Fee")
    @JSONField(name = "金额")
    private BigDecimal amount;

}
