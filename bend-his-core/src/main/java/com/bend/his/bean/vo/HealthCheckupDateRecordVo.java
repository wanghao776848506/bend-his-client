package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 个人健康体检记录日期列表
 */
@Data
@ApiModel(value = "HealthCheckupDateRecordVo - 个人健康体检记录日期列表", description = "个人健康体检记录日期列表")
public class HealthCheckupDateRecordVo extends BaseEntity {

    @ApiModelProperty(notes = "居民ID")
    @JSONField(name = "PersonID")
    private String personId;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getAuthCode());
        inputJson.put("PersonID", this.getPersonId());
        return inputJson.toJSONString();
    }

}
