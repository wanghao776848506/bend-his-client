package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  病人
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PatientDto - 病人或患者 ",description = "病人或患者")
public class PatientDto extends AbstractBaseEntity{

    @ApiModelProperty(notes = "病人姓名")
    @JSONField(name = "病人姓名")
    private String patientName;

    @ApiModelProperty(notes = "病人性别")
    @JSONField(name = "病人性别")
    private String patientSex;

    @ApiModelProperty(notes = "身份证号码")
    @JSONField(name = "身份证号码")
    private String idCardNo;

    @ApiModelProperty(notes = "挂号ID")
    @JSONField(name = "ID")
    private String registrationId;

    @ApiModelProperty(notes = "挂号CODE")
    @JSONField(name = "CODE")
    private String registrationCode;

    @ApiModelProperty(notes = "科室编码或科室ID")
    @JSONField(name = "科室ID")
    private String departmentId;

    @ApiModelProperty(notes = "医生ID")
    @JSONField(name = "医生ID")
    private String doctorId;

}
