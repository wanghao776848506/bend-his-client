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
 * 费用账单/清单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ExpenseBillDto - 费用清单/账单 ",description = "费用清单/账单")
public class ExpenseBillDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "收费记录ID")
    @JSONField(name = "收费记录ID")
    private String chargeRecordId;


    /*response data*/
    @ApiModelProperty(notes = "清单ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "处方ID/CFID")
    @JSONField(name = "CFID")
    private String recipeId;

    @ApiModelProperty(notes = "处方号/CFCODE")
    @JSONField(name = "CFCODE")
    private String recipeCode;

    @ApiModelProperty(notes = "费用名称/FeeName")
    @JSONField(name = "FeeName")
    private String feeName;

    @ApiModelProperty(notes = "项目名称[费用]")
    @JSONField(name = "ItemName")
    private String costItemName;

    @ApiModelProperty(notes = "单位/Unit")
    @JSONField(name = "Unit")
    private String unit;

    @ApiModelProperty(notes = "规格/Spec")
    @JSONField(name = "Spec")
    private String specification;

    @ApiModelProperty(notes = "单价/Price")
    @JSONField(name = "Price")
    private BigDecimal unitPrice;

    @ApiModelProperty(notes = "数量/Amount")
    @JSONField(name = "Amount")
    private Integer quantity;

    @ApiModelProperty(notes = "金额/小计/Fee")
    @JSONField(name = "Fee")
    private BigDecimal amount;

    @ApiModelProperty(notes = "经办人/开单人")
    @JSONField(name = "Oper")
    private String operator;

    @ApiModelProperty(notes = "开单科室/OperDept")
    @JSONField(name = "OperDept")
    private String billDepartment;

    @ApiModelProperty(notes = "开单时间/OperDate")
    @JSONField(name = "OperDate")
    private String billTime;


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("收费记录ID", this.getChargeRecordId());
        return inputJson.toJSONString();
    }
}
