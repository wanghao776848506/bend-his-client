package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
@ApiModel(value = "ICD10Dto - ICD10标准病种", description = "ICD10")
public class ICD10Dto {
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
}
