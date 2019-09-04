package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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



}
