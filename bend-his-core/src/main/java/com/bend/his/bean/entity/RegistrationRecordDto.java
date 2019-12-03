package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RegistrationRecordDto - 门诊挂号记录", description = "门诊挂号记录")
public class RegistrationRecordDto extends AbstractBaseEntity {


    @ApiModelProperty(notes = "挂号ID")
    @JSONField(name = "挂号ID")
    private String registrationId;

    @ApiModelProperty(notes = "挂号时间")
    @JSONField(name = "挂号时间")
    private String createDate;

    @ApiModelProperty(notes = "机构名称")
    @JSONField(name = "ORG_NAME")
    private String orgName;

    @ApiModelProperty(notes = "门诊号")
    @JSONField(name = "门诊号")
    private String outpatientNumber;

    @ApiModelProperty(notes = "就诊序号")
    @JSONField(name = "就诊序号")
    private String seq;

    @ApiModelProperty(notes = "科室名称")
    @JSONField(name = "科室")
    private String departmentName;

    @ApiModelProperty(notes = "医生名称")
    @JSONField(name = "医生")
    private String doctorName;

    @ApiModelProperty(notes = "是否可退[0是1否]")
    @JSONField(name = "是否可退")
    private String refundAble;
}
