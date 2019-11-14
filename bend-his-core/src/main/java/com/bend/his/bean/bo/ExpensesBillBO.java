package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ExpensesBillBO - 门诊费用清单详情", description = "清单明细/QdList/该处方的清单明细[列表]")
public class ExpensesBillBO {

    @ApiModelProperty(notes = "处方ID/CFID")
    @JSONField(name = "CFID")
    private String recipeId;

    @ApiModelProperty(notes = "处方医生/Oper")
    @JSONField(name = "Oper")
    private String billDoctor;

    @ApiModelProperty(notes = "开单科室/OperDept")
    @JSONField(name = "OperDept")
    private String billDepartment;

    @ApiModelProperty(notes = "开单时间/OperDate")
    @JSONField(name = "OperDate")
    private String billTime;

    @ApiModelProperty(notes = "数量")
    @JSONField(name = "Amount")
    private String amount;

    @ApiModelProperty(notes = "金额")
    @JSONField(name = "Fee")
    private String fee;

    @ApiModelProperty(notes = "单位/Unit")
    @JSONField(name = "Unit")
    private String unit;

    @ApiModelProperty(notes = "费用名称/FeeName")
    @JSONField(name = "FeeName")
    private String feeName;

    @ApiModelProperty(notes = "执行科室名称")
    @JSONField(name = "ActDept")
    private String operateDepartmentName;

    @ApiModelProperty(notes = "单价/Price")
    @JSONField(name = "Price")
    private BigDecimal unitPrice;

    @ApiModelProperty(notes = "项目名称[费用]")
    @JSONField(name = "ItemName")
    private String costItemName;

    @ApiModelProperty(notes = "ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "规格")
    @JSONField(name = "Spec")
    private String specification;

    @ApiModelProperty(notes = "处方号/CFCODE")
    @JSONField(name = "CFCODE")
    private String recipeCode;



}
