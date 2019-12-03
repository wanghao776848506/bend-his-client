package com.bend.his.bean.entity;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 医院三大目录查询(药品、诊疗、耗材)
 */
@Data
@ApiModel(value = "HospitalThreeCatalogueDto - 医院三大目录查询(药品、诊疗、耗材)", description = "医院三大目录查询(药品、诊疗、耗材)")
public class HospitalThreeCatalogueDto extends AbstractBaseEntity {
    /*response data*/
    @ApiModelProperty(notes = "目录名称")
    @JSONField(name = "目录名称")
    private String directoryName;

    @ApiModelProperty(notes = "目录编码")
    @JSONField(name = "目录编码")
    private String directoryCode;

    @ApiModelProperty(notes = "目录CODE")
    @JSONField(name = "目录CODE")
    private String code;


    @ApiModelProperty(notes = "助记码")
    @JSONField(name = "助记码")
    private String mnemonicCode;

    @ApiModelProperty(notes = "目录类别名称")
    @JSONField(name = "目录类别名称")
    private String directoryCategoryName;

    @ApiModelProperty(notes = "单位")
    @JSONField(name = "单位")
    private String unit;

    @ApiModelProperty(notes = "规格")
    @JSONField(name = "规格")
    private String specification;

    @ApiModelProperty(notes = "剂型")
    @JSONField(name = "剂型")
    private String formulation;

    @ApiModelProperty(notes = "价格")
    @JSONField(name = "价格")
    private String price;

    @ApiModelProperty(notes = "生产厂家名称")
    @JSONField(name = "生产厂家名称")
    private String manufacturerName;

    @ApiModelProperty(notes = "创建时间")
    @JSONField(name = "创建时间")
    private String createDate;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "备注")
    private String remark;

    @ApiModelProperty(notes = "行数")
    @JSONField(name = "行数")
    private String rows;

}
