package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "RegistrationRecordDto - 门诊挂号记录", description = "门诊挂号记录")
public class RegistrationRecordDto {


    @ApiModelProperty(notes = "挂号ID")
    @JSONField(name = "挂号ID")
    private String registrationId;

    @ApiModelProperty(notes = "挂号时间")
    @JSONField(name = "挂号时间")
    private String createDate;

    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构ID")
    private String organizationCode;

    @ApiModelProperty(notes = "门诊号")
    @JSONField(name = "门诊号")
    private String outpatientNumber;

    @ApiModelProperty(notes = "就诊序号")
    @JSONField(name = "就诊序号")
    private String seq;

    @ApiModelProperty(notes = "科室编码或科室ID")
    @JSONField(name = "科室ID")
    private String departmentId;

    @ApiModelProperty(notes = "科室名称")
    @JSONField(name = "科室")
    private String departmentName;

    @ApiModelProperty(notes = "医生ID")
    @JSONField(name = "医生ID")
    private String doctorId;

    @ApiModelProperty(notes = "医生名称")
    @JSONField(name = "医生")
    private String doctorName;

    @ApiModelProperty(notes = "是否可退")
    @JSONField(name = "是否可退")
    private String refundAble;
}
