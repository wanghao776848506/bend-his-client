package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 个人健康体检记录日期列表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HealthCheckupDateRecordDto - 个人健康体检记录日期列表", description = "个人健康体检记录日期列表")
public class HealthCheckupDateRecordDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "验证码/产品验证码")
    @JSONField(name = "ProductCode")
    private String productCode;

    @ApiModelProperty(notes = "居民ID")
    @JSONField(name = "PersonID")
    private String personId;
    /*
    *
    "MtId": "B9DF8A1894554A85A9645A72F90E3C5C",
      "ExamDate": "2016-08-04"
    */
    @ApiModelProperty(notes = "随访ID")
    @JSONField(name = "MtID")
    private String mtId;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getProductCode());
        inputJson.put("PersonID", this.getPersonId());
        return inputJson.toJSONString();
    }

}
