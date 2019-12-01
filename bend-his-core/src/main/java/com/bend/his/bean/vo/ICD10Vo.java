package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
@ApiModel(value = "ICD10Vo - ICD10标准病种", description = "ICD10")
public class ICD10Vo extends BaseEntity {
    @ApiModelProperty(notes = "病种名称")
    @JSONField(name = "病种名称")
    private String diseaseName;

    @ApiModelProperty(notes = "疾病类别")
    @JSONField(name = "疾病类别")
    private String diseaseCategory;

    @ApiModelProperty(notes = "开始行数")
    @JSONField(name = "开始行数")
    private String beginRowNum;

    @ApiModelProperty(notes = "结束行数")
    @JSONField(name = "结束行数")
    private String endRowNum;

    @ApiModelProperty(notes = "开始时间,格式:yyyy-MM-dd hh:mm:ss")
    @JSONField(name = "开始时间")
    private String beginTime;

    @ApiModelProperty(notes = "结束时间,格式:yyyy-MM-dd hh:mm:ss")
    @JSONField(name = "结束时间")
    private String endTime;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("病种名称", this.getDiseaseName());
        inputJson.put("开始行数", this.getBeginRowNum());
        inputJson.put("结束行数", this.getEndRowNum());
        inputJson.put("开始时间", this.getBeginTime());
        inputJson.put("结束时间", this.getEndTime());
        inputJson.put("疾病类别", this.getDiseaseCategory());
        return inputJson.toJSONString();
    }

}
