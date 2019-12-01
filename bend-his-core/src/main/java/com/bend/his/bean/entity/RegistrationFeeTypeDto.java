package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 病人挂号-挂号费用类型
 */
@Getter
@Setter
@ApiModel(value = "RegistrationFeeTypeDto - 挂号费用类型", description = "挂号费用类型")
public class RegistrationFeeTypeDto {

    /*response data*/
    @ApiModelProperty(notes = "费用类型ID")
    @JSONField(name = "费用类型ID")
    private String feeTypeId;

    @ApiModelProperty(notes = "费用类型名称")
    @JSONField(name = "费用类型名称")
    private String feeTypeName;
}
