package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 检查检验_申请表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "InspectionApplyFormDto - 检查检验申请表", description = "检查检验申请表")
public class InspectionApplyFormDto extends AbstractBaseEntity {

    /*request data*/
    @ApiModelProperty(notes = "业务ID,[门诊ID查询门诊对应的检查检验，住院ID查询住院的]")
    @JSONField(name = "业务ID")
    private String businessId;

    /*@ApiModelProperty(notes = "住院ID/业务ID")
    @JSONField(name = "住院ID")
    private String hospitalizationId;*/

    /*response data*/
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

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("业务ID", this.getBusinessId());
        return inputJson.toJSONString();
    }






}
