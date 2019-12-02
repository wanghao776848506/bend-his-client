package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 检查检验_申请表
 */
@Getter
@Setter
@ApiModel(value = "InspectionApplyFormDto - 检查检验申请表", description = "检查检验申请表")
public class InspectionApplyFormDto {

    @ApiModelProperty(notes = "申请ID/申请单ID")
    @JSONField(name = "申请单ID")
    private String applyId;

    @ApiModelProperty(notes = "申请Item名称")
    @JSONField(name = "名称")
    private String applyItemName;

    @ApiModelProperty(notes = "开单时间")
    @JSONField(name = "开单时间")
    private String billTime;

    @ApiModelProperty(notes = "开单人")
    @JSONField(name = "开单人")
    private String operator;
}
