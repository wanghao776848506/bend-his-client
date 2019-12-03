package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门诊费用清单查询
 */
@Data
@ApiModel(value = "OutpatientExpensesBillVo - 门诊费用清单", description = "门诊费用清单")
public class OutpatientExpensesBillVo extends BaseEntity {

    @ApiModelProperty(notes = "挂号ID")
    @JSONField(name = "挂号ID")
    private String registrationId;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("挂号ID", this.getRegistrationId());
        return inputJson.toJSONString();
    }

}
