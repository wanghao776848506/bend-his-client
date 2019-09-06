package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 住院医嘱
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MedicalOrderDto - 医嘱", description = "住院医嘱")
public class MedicalOrderDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "业务ID")
    @JSONField(name = "业务ID")
    private String businessId;

    @ApiModelProperty(notes = "开始时间")
    @JSONField(name = "开始时间")
    private String beginTime;

    @ApiModelProperty(notes = "结束时间,[医嘱开具时间：2014-01-01 15:11:22]")
    @JSONField(name = "结束时间")
    private String endTime;

    /*response data*/
    @ApiModelProperty(notes = "医嘱ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "住院号")
    @JSONField(name = "住院号")
    private String hospitalizationNo;

    @ApiModelProperty(notes = "医嘱记录序号")
    @JSONField(name = "医嘱记录序号")
    private String medicalRecordId;

    @ApiModelProperty(notes = "医嘱内容")
    @JSONField(name = "医嘱内容")
    private String medicalContent;

    @ApiModelProperty(notes = "医嘱开具时间")
    @JSONField(name = "医嘱开具时间")
    private String medicalCreateDate;

    @ApiModelProperty(notes = "医嘱开始时间")
    @JSONField(name = "医嘱开始时间")
    private String medicalBeginTime;

    @ApiModelProperty(notes = "停嘱时间")
    @JSONField(name = "停嘱时间")
    private String medicalOrderEndTime;

    @ApiModelProperty(notes = "给药途径")
    @JSONField(name = "给药途径")
    private String drugDelivery;

    @ApiModelProperty(notes = "频次")
    @JSONField(name = "频次")
    private String frequency;

    @ApiModelProperty(notes = "单次剂量")
    @JSONField(name = "单次剂量")
    private String singleDose;

    @ApiModelProperty(notes = "医生编码/医生ID")
    @JSONField(name = "医生编码")
    private String doctorId;

    @ApiModelProperty(notes = "医生或医生名称/医生姓名")
    @JSONField(name = "医生姓名")
    private String doctorName;

    @ApiModelProperty(notes = "类型编码/类型ID")
    @JSONField(name = "类型编码")
    private Integer typeCode;

    @ApiModelProperty(notes = "类型名称")
    @JSONField(name = "类型名称")
    private String typeName;

    @ApiModelProperty(notes = "医嘱分类")
    @JSONField(name = "医嘱分类")
    private String medicalCategory;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("业务ID", this.getBusinessId());
        inputJson.put("开始时间", this.getBeginTime());
        inputJson.put("结束时间", this.getEndTime());
        return inputJson.toJSONString();
    }
}
