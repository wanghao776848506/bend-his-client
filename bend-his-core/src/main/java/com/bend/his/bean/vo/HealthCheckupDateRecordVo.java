package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 个人健康体检记录日期列表
 */
@Getter
@Setter
@ApiModel(value = "HealthCheckupDateRecordVo - 个人健康体检记录日期列表", description = "个人健康体检记录日期列表")
public class HealthCheckupDateRecordVo extends BaseEntity {

    @ApiModelProperty(notes = "验证码/产品验证码")
    @JSONField(name = "ProductCode")
    private String productCode;

    @ApiModelProperty(notes = "居民ID")
    @JSONField(name = "PersonID")
    private String personId;

    @ApiModelProperty(notes = "随访ID")
    @JSONField(name = "MtID")
    private String mtId;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getProductCode());
        inputJson.put("PersonID", this.getPersonId());
        return inputJson.toJSONString();
    }

}
