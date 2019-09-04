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
 * 住院费用明细
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HospitalizationFeeDto - 住院费用明细",description = "住院费用明细")
public class HospitalizationFeeDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "住院号")
    @JSONField(name = "住院号")
    private String hospitalizationNo;

    @ApiModelProperty(notes = "业务ID:住院ID")
    @JSONField(name = "业务ID")
    private String businessId;

    @ApiModelProperty(notes = "开始时间")
    @JSONField(name = "开始时间")
    private String beginTime;

    @ApiModelProperty(notes = "结束时间")
    @JSONField(name = "结束时间")
    private String endTime;

    @ApiModelProperty(notes = "状态[0表示清单不包含退药、退费等产生的负数记录；1表示清单包含退药、退费等产生的负数记录。（非必填项：默认为0 ）]")
    @JSONField(name = "状态")
    private Integer state;

    /*response data*/
//    @ApiModelProperty(notes = "住院号")
//    @JSONField(name = "住院号")
//    private String hospitalizationNo;

    @ApiModelProperty(notes = "费用明细ID")
    @JSONField(name = "费用明细ID")
    private String costDetailId;

    @ApiModelProperty(notes = "项目名称[费用]")
    @JSONField(name = "项目名称")
    private String costItemName;

    @ApiModelProperty(notes = "项目编码[费用]")
    @JSONField(name = "项目编码")
    private String costItemCode;

    @ApiModelProperty(notes = "项目类别名称[费用]")
    @JSONField(name = "项目类别名称")
    private String costItemCategoryName;

    @ApiModelProperty(notes = "项目类别编码[费用]")
    @JSONField(name = "项目类别编码")
    private String costItemCategoryCode;

    @ApiModelProperty(notes = "单位")
    @JSONField(name = "单位")
    private String unit;

    @ApiModelProperty(notes = "剂型")
    @JSONField(name = "剂型")
    private String formulation;

    @ApiModelProperty(notes = "规格")
    @JSONField(name = "规格")
    private String specification;

    @ApiModelProperty(notes = "单价")
    @JSONField(name = "单价")
    private BigDecimal unitPrice;

    @ApiModelProperty(notes = "数量")
    @JSONField(name = "数量")
    private Integer quantity;

    @ApiModelProperty(notes = "金额")
    @JSONField(name = "金额")
    private BigDecimal amount;

    @ApiModelProperty(notes = "用量")
    @JSONField(name = "用量")
    private String dosage;

    @ApiModelProperty(notes = "用法")
    @JSONField(name = "用法")
    private String usage;

    @ApiModelProperty(notes = "用药天数")
    @JSONField(name = "用药天数")
    private Integer medicateDays;

    @ApiModelProperty(notes = "医院计价单位")
    @JSONField(name = "医院计价单位")
    private String hospitalPricingUnit;

    @ApiModelProperty(notes = "是否进口药品")
    @JSONField(name = "是否进口药品")
    private String isImportedDrugs;

    @ApiModelProperty(notes = "药品产地")
    @JSONField(name = "药品产地")
    private String drugProducingArea;

    @ApiModelProperty(notes = "处方号")
    @JSONField(name = "处方号")
    private String recipeCode;

    @ApiModelProperty(notes = "费用单据类型")
    @JSONField(name = "费用单据类型")
    private String costDocumentType;

    @ApiModelProperty(notes = "开单科室名称")
    @JSONField(name = "开单科室名称")
    private String billDepartmentName;

    @ApiModelProperty(notes = "开单科室编码")
    @JSONField(name = "开单科室编码")
    private String billDepartmentId;

    @ApiModelProperty(notes = "开单医生姓名")
    @JSONField(name = "开单医生姓名")
    private String billDoctorName;

    @ApiModelProperty(notes = "开单医生编码")
    @JSONField(name = "开单医生编码")
    private String billDoctorId;

    @ApiModelProperty(notes = "开单时间")
    @JSONField(name = "开单时间")
    private String billTime;

    @ApiModelProperty(notes = "执行科室名称")
    @JSONField(name = "执行科室名称")
    private String operateDepartmentName;

    @ApiModelProperty(notes = "执行科室编码")
    @JSONField(name = "执行科室编码")
    private String operateDepartmentId;

    @ApiModelProperty(notes = "执行医生姓名")
    @JSONField(name = "执行医生姓名")
    private String operateDoctorName;

    @ApiModelProperty(notes = "执行医生编码")
    @JSONField(name = "执行医生编码")
    private String operateDoctorId;

    @ApiModelProperty(notes = "执行时间")
    @JSONField(name = "执行时间")
    private String operateTime;

    @ApiModelProperty(notes = "处方医师")
    @JSONField(name = "处方医师")
    private String prescriptionDoctor;

    @ApiModelProperty(notes = "经办人")
    @JSONField(name = "经办人")
    private String operator;

    @ApiModelProperty(notes = "执业医师证号")
    @JSONField(name = "执业医师证号")
    private String practiceDoctorNumber;

    @ApiModelProperty(notes = "费用冲销ID")
    @JSONField(name = "费用冲销ID")
    private String costWriteOffId;

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "机构名称")
    @JSONField(name = "机构名称")
    private String organizationName;


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("业务ID", this.getBusinessId());
        inputJson.put("住院号", this.getHospitalizationNo());
        inputJson.put("开始时间", this.getBeginTime());
        inputJson.put("结束时间", this.getEndTime());
        inputJson.put("状态", this.getState());
        return inputJson.toJSONString();
    }
}
