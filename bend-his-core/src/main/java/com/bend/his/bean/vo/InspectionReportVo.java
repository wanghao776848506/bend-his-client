package com.bend.his.bean.vo;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.InspectionReportBO;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 检查检验报告/结果
 */
@Data
@ApiModel(value = "InspectionReportVo - 检查检验报告/结果", description = "检查检验报告/结果")
public class InspectionReportVo extends BaseEntity {

    /*request data*/
    @ApiModelProperty(notes = "申请ID/申请单ID[30-15接口获取]")
    @JSONField(name = "申请单ID")
    private String applyId;

    @ApiModelProperty(notes = "患者信息、标本号、结论、结果等", hidden = true)
    @JSONField(name = "perInfo")
    private String perInfo;

    @ApiModelProperty(notes = "指标值、参考值等", hidden = true)
    @JSONField(name = "idx")
    private String idx;

    @ApiModelProperty(notes = "检查检验报告")
    private InspectionReportBO inspectionReportBO;


    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("申请单ID", this.getApplyId());
        return inputJson.toJSONString();
    }


}
