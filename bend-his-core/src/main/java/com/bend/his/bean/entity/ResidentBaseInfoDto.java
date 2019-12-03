package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/* 返回值


"REGION_NAME": "",
"ORGID": "机构ID",
"ORGNAME": "机构名称",
*/
@Data
@ApiModel(value = "ResidentBaseInfoDto - 居民健康档案", description = "居民健康档案")
public class ResidentBaseInfoDto extends AbstractBaseEntity {
    /*response data*/
    @ApiModelProperty(notes = "居民ID -> 接口返回值")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "居民年龄")
    @JSONField(name = "AGE")
    private String age;

    @ApiModelProperty(notes = "出生日期")
    @JSONField(name = "BIRTHDAY")
    private String birthday;

    @ApiModelProperty(notes = "居民性别")
    @JSONField(name = "SEX")
    private String sex;

    @ApiModelProperty(notes = "家庭ID")
    @JSONField(name = "FAMILYID")
    private String familyId;

    @ApiModelProperty(notes = "慢病种类")
    @JSONField(name = "CMKIND")
    private String cmkind;

    @ApiModelProperty(notes = "现住址")
    @JSONField(name = "CURRENTADDRESS")
    private String currentAddress;

    @ApiModelProperty(notes = "自定义编码")
    @JSONField(name = "CUSTOM_NUMBER")
    private String customNumber;

    @ApiModelProperty(notes = "贫困人口（2 是，1 否）")
    @JSONField(name = "ISPOOR")
    private String isPoor;

    @ApiModelProperty(notes = "档案状态（ 0 活动 1 迁出 2 死亡 3 其他  99 已删除）")
    @JSONField(name = "HRSTATUS")
    private String hrStatus;

    @ApiModelProperty(notes = "区划地址")
    @JSONField(name = "REGION_NAME")
    private String regionName;

    @ApiModelProperty(notes = "机构ID/机构编码")
    @JSONField(name = "ORGID")
    private String organizationCode;

    @ApiModelProperty(notes = "机构名称")
    @JSONField(name = "ORGNAME")
    private String orgName;

    @ApiModelProperty(notes = "当前行数")
    @JSONField(name = "R__N")
    private String rowNum;
}
