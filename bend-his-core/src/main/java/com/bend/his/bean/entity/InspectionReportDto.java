package com.bend.his.bean.entity;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 检查检验报告/结果
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "InspectionReportDto - 检查检验报告/结果", description = "检查检验报告/结果")
public class InspectionReportDto extends AbstractBaseEntity {

    /*request data*/
    @ApiModelProperty(notes = "申请ID/申请单ID[30-15接口获取]")
    @JSONField(name = "申请单ID")
    private String applyId;

    @ApiModelProperty(notes = "收费人员ID/操作员ID")
    @JSONField(name = "操作员ID")
    private String userId;

    @ApiModelProperty(notes = "虚拟收费人员ID/虚拟操作人员ID")
    @JSONField(name = "虚拟操作人员ID")
    private String vmUserId;
    /*response data*/

    @ApiModelProperty(notes = "患者信息、标本号、结论、结果等")
    @JSONField(name = "perInfo")
    private String perInfo;

    @ApiModelProperty(notes = "指标值、参考值等")
    @JSONField(name = "idx")
    private String idx;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("申请单ID", this.getApplyId());
        inputJson.put("虚拟操作人员ID", this.getVmUserId());
        return inputJson.toJSONString();
    }


}
