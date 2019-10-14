package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PairCodeBO - 对码详细", description = "对码详细")
public class PairCodeBO {

    @ApiModelProperty(notes = "基卫目录编码[05 交易中返回的‘目录编码’]")
    @JSONField(name = "基卫目录编码")
    private String basicDirectoryCode;

    @ApiModelProperty(notes = "社保目录类别[1：药品，3：耗材，4：诊疗]")
    @JSONField(name = "社保目录类别")
    private String insuranceCatalogDirectory;

    @ApiModelProperty(notes = "社保目录ID")
    @JSONField(name = "社保目录ID")
    private String insuranceCatalogId;

    @ApiModelProperty(notes = "社保目录编码")
    @JSONField(name = "社保目录编码")
    private String insuranceCatalogCode;

    @ApiModelProperty(notes = "社保目录名称")
    @JSONField(name = "社保目录名称")
    private String insuranceCatalogName;

    @ApiModelProperty(notes = "拼音")
    @JSONField(name = "拼音")
    private String pinyin;

    @ApiModelProperty(notes = "剂型")
    @JSONField(name = "剂型")
    private String formulation;

    @ApiModelProperty(notes = "剂型编码")
    @JSONField(name = "剂型编码")
    private String formulationCode;

    @ApiModelProperty(notes = "规格")
    @JSONField(name = "规格")
    private String specification;

    @ApiModelProperty(notes = "规格编码")
    @JSONField(name = "规格编码")
    private String specificationCode;

    @ApiModelProperty(notes = "单位")
    @JSONField(name = "单位")
    private String unit;

    @ApiModelProperty(notes = "进价")
    @JSONField(name = "进价")
    private String purchasePrice;

    @ApiModelProperty(notes = "售价")
    @JSONField(name = "售价")
    private String price;

    @ApiModelProperty(notes = "生产厂家")
    @JSONField(name = "生产厂家")
    private String manufacturer;

    @ApiModelProperty(notes = "收费级别")
    @JSONField(name = "收费级别")
    private String chargeLevel;

    @ApiModelProperty(notes = "限制用药")
    @JSONField(name = "限制用药")
    private String restrictedMedication;

    @ApiModelProperty(name = "备注", notes = "备注")
    @JSONField(name = "备注")
    private String remark;
}
