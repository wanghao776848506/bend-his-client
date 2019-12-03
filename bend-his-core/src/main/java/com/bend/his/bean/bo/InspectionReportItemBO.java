package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检验检查项目明细
 */
@Data
@ApiModel(value = "InspectionReportItemBO - 检查检验项目明细", description = "检查检验项目明细")
public class InspectionReportItemBO {

    @ApiModelProperty(notes = "生成时间")
    @JSONField(name = "CreateTime")
    private String createTime;

    @ApiModelProperty(notes = "更新时间")
    @JSONField(name = "UpdateTime")
    private String updateTime;

    @ApiModelProperty(notes = "检查项ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "金额")
    @JSONField(name = "FEE")
    private String fee;

    @ApiModelProperty(notes = "排序")
    @JSONField(name = "Sn")
    private String sn;

    @ApiModelProperty(notes = "单位")
    @JSONField(name = "Unit")
    private String unit;

    @ApiModelProperty(notes = "项目名称")
    @JSONField(name = "Name")
    private String name;

    @ApiModelProperty(notes = "指标值、参考值等", hidden = true)
    @JSONField(name = "idx")
    private String idx;

    @ApiModelProperty(notes = "指标值、参考值等", hidden = true)
    @JSONField(name = "tagCode")
    private String tagCode;

}
