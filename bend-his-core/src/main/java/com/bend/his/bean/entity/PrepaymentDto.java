package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 住院预交费
 */
@Getter
@Setter
@ApiModel(value = "PrepaymentDto - 住院预交费", description = "住院预交费")
public class PrepaymentDto {

    /*response data*/
    @ApiModelProperty(notes = "ID/住院预交费ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "CODE/缴费流水号")
    @JSONField(name = "CODE")
    private String paySNCode;

    @ApiModelProperty(notes = "SYSCODE/基层流水号")
    @JSONField(name = "SYSCODE")
    private String sysCode;

    @ApiModelProperty(notes = "金额/FEE")
    @JSONField(name = "FEE")
    private BigDecimal fee;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "REMARK")
    private String remark;

    @ApiModelProperty(notes = "收费时间")
    @JSONField(name = "CREATE_TIME")
    private Date createDate;

    @ApiModelProperty(notes = "收费员姓名/操作员")
    @JSONField(name = "NAME")
    private String userName;

}
