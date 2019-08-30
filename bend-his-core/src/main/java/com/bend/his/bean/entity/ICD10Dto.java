package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "ICD10")
public class ICD10Dto extends AbstractBaseEntity{

    @ApiModelProperty(notes = "病种名称")
    @JSONField(name = "病种名称",ordinal = 1)
    private String diseaseName;

    @ApiModelProperty(notes = "疾病类别")
    @JSONField(name = "疾病类别",ordinal = 2)
    private String diseaseCategory;

    @ApiModelProperty(notes = "开始行数")
    @JSONField(name = "开始行数",ordinal = 3)
    private String beginRowNum;

    @ApiModelProperty(notes = "结束行数")
    @JSONField(name = "结束行数",ordinal = 4)
    private String endRowNum;

    @ApiModelProperty(notes = "开始时间")
    @JSONField(name = "开始时间",ordinal = 5)
    private String beginTime;

    @ApiModelProperty(notes = "结束时间")
    @JSONField(name = "结束时间",ordinal = 6)
    private String endTime;


    /*response data*/
    @ApiModelProperty(notes = "疾病编码")
    @JSONField(name = "疾病编码")
    private String diseaseCode;

    @ApiModelProperty(notes = "助记码")
    @JSONField(name = "助记码")
    private String mnemonicCode;

    @ApiModelProperty(notes = "创建时间")
    @JSONField(name = "创建时间")
    private String createDate;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "备注")
    private String remark;

    @ApiModelProperty(notes = "疾病ID")
    @JSONField(name = "疾病ID")
    private String diseaseId;

    @ApiModelProperty(notes = "行数")
    @JSONField(name = "行数")
    private String rows;


    @Override
    public String createJSONObject() {
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
