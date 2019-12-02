package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ApiModel(value = "VACCBO - 非免疫规划预防接种史",description = "非免疫规划预防接种史")
public class VACCBO {
    /**
     "ID": "8695F3105D97463084F579AE2F1FF504",
     "DataMtID": "037B5B1D2C574BD8B2EF6CE2794935DC",
     "VaccineName": null,
     "VaccDate": null,
     "VaccOrgName": null,
     "ExamDate": "\/Date(1513267200000)\/",
     "Source": 0
     */
    @ApiModelProperty(notes = "")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "随访ID")
    @JSONField(name = "DataMtID")
    private String dataMtID;

    @ApiModelProperty(notes = "疫苗名称")
    @JSONField(name = "VaccineName")
    private String vaccineName;

    @ApiModelProperty(notes = "接种日期")
    @JSONField(name = "VaccDate")
    private String VaccDate;

    @ApiModelProperty(notes = "接种机构")
    @JSONField(name = "VaccOrgName")
    private String VaccOrgName;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;



}
