package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 健康史
 "ID": "信息ID",
 "PersonID": "个人ID",
 "RecordType": "类型:1手术 2外伤 3输血 4遗传病史",
 "Name": "名称",
 "Code": "编码",
 "Value": "值",
 "OccurrenceDate": "发生日期"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HealthHistory - 个人健康史", description = "个人健康史")
public class HealthHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "信息ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "个人ID")
    @JSONField(name = "PersonID")
    private String personID;

    @ApiModelProperty(notes = "类型:1手术 2外伤 3输血 4遗传病史")
    @JSONField(name = "RecordType")
    private String recordType;

    @ApiModelProperty(notes = "名称")
    @JSONField(name = "Name")
    private String name;

    @ApiModelProperty(notes = "编码")
    @JSONField(name = "Code")
    private String code;

    @ApiModelProperty(notes = "值")
    @JSONField(name = "Value")
    private String value;

    @ApiModelProperty(notes = "发生日期")
    @JSONField(name = "OccurrenceDate")
    private String occurrenceDate;
}
