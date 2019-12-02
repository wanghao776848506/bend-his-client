package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 检查检验_申请表
 */
@Getter
@Setter
@ApiModel(value = "InspectionApplyFormVo - 检查检验申请表", description = "检查检验申请表")
public class InspectionApplyFormVo extends BaseEntity {

    @ApiModelProperty(notes = "业务ID,[门诊ID查询门诊对应的检查检验，住院ID查询住院的]")
    @JSONField(name = "业务ID")
    private String businessId;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("业务ID", this.getBusinessId());
        return inputJson.toJSONString();
    }


}
