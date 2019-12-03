package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 住院病人
 */
@Data
@ApiModel(value = "InpatientRecordDto - 病人住院记录", description = "病人住院记录")
public class InpatientRecordDto extends AbstractBaseEntity {
    /*response data*/
    @ApiModelProperty(notes = "住院记录ID")
    @JSONField(name = "ID")
    private String recordId;

    @ApiModelProperty(notes = "住院号")
    @JSONField(name = "住院号")
    private String hospitalizationNo;

    @ApiModelProperty(notes = "病人姓名，不为空时优先通过姓名检索在院数据")
    @JSONField(name = "姓名")
    private String patientName;

    @ApiModelProperty(notes = "入院时间")
    @JSONField(name = "入院时间")
    private String admissionDate;

    @ApiModelProperty(notes = "出院时间")
    @JSONField(name = "出院时间")
    private String leaveHospitalDate;

    @ApiModelProperty(notes = "医院名称")
    @JSONField(name = "医院名称")
    private String hospitalName;

    @ApiModelProperty(notes = "入院科室")
    @JSONField(name = "入院科室")
    private String inDepartmentName;

    @ApiModelProperty(notes = "住院总费用/总费用")
    @JSONField(name = "总费用")
    private BigDecimal totalFee;

    @ApiModelProperty(notes = "住院ID/业务ID,不同场景下参数，功能一致")
    @JSONField(name = "住院ID")
    private String hospitalizationId;


}
