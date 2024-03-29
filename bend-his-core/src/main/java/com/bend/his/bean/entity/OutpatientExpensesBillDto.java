package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.ExpensesBillBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * 门诊费用清单查询
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OutpatientExpensesBillDto - 门诊费用清单", description = "门诊费用清单")
public class OutpatientExpensesBillDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "挂号ID")
    @JSONField(name = "挂号ID")
    private String registrationId;

    /*response data*/
    @ApiModelProperty(notes = "处方号/CFCODE")
    @JSONField(name = "CFCODE")
    private String recipeCode;

    @ApiModelProperty(notes = "处方ID/CFID")
    @JSONField(name = "CFID")
    private String recipeId;

    @ApiModelProperty(notes = "处方费用/CFFee")
    @JSONField(name = "CFFee")
    private BigDecimal recipeFee;

    @ApiModelProperty(notes = "处方医生/Oper")
    @JSONField(name = "Oper")
    private String billDoctor;

    @ApiModelProperty(notes = "开单科室/OperDept")
    @JSONField(name = "OperDept")
    private String billDepartment;

    @ApiModelProperty(notes = "开单时间/OperDate")
    @JSONField(name = "OperDate")
    private String billTime;

    @ApiModelProperty(notes = "清单明细/QdList/该处方的清单明细[列表]", hidden = true)
    @JSONField(name = "QdList")
    private String prescriptionDetail;
    /**
     * 该处方的清单明细
     */
    @ApiModelProperty(notes = "清单明细/QdList/该处方的清单明细[列表]")
    private List<ExpensesBillBO> expensesBillList;


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("挂号ID", this.getRegistrationId());
        return inputJson.toJSONString();
    }

}
