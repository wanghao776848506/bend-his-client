package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResidentBaseInfoDto - 居民健康档案", description = "居民健康档案")
public class ResidentBaseInfoDto extends AbstractBaseEntity {
    /*request params*/
    @ApiModelProperty(notes = "验证码/产品验证码")
    @JSONField(name = "ProductCode")
    private String productCode;

    @ApiModelProperty(notes = "居民姓名")
    @JSONField(name = "NAME")
    private String name;

    @ApiModelProperty(notes = "区划编码")
    @JSONField(name = "RegionCode")
    private String regionCode;

    @ApiModelProperty(notes = "状态,默认为活动，-1:全部（不包含删除的）,0活动,1迁出,2死亡,99已删除,3其他,4失访")
    @JSONField(name = "Status")
    private String status;

    @ApiModelProperty(notes = "居民身份证")
    @JSONField(name = "IDCARD")
    private String idCardNo;

    @ApiModelProperty(notes = "居民ID")
    @JSONField(name = "PERSONID")
    private String personId;

    @ApiModelProperty(notes = "档案号/人员健康档案编号")
    @JSONField(name = "CODE")
    private String personCode;

    @ApiModelProperty(notes = "居民类型[01 一般人群,02 慢病疾病人群,03 老年人,04 0-6岁儿童,05 孕产妇]")
    @JSONField(name = "PERSONTYPE")
    private String personType;

    @ApiModelProperty(notes = "居民类型值[一般人群--01 适龄母亲,02 适龄父亲,03 适龄妇女,慢病疾病--01 高血压,02 糖尿病,03 重性精神病,04 高塘合并,老年人----01 老年人,0-6岁儿童----01 0-6岁儿童,02 体弱儿,孕产妇----01 孕产妇, 02 高危孕产妇]")
    @JSONField(name = "TYPEVALUE")
    private String typeValue;


    @ApiModelProperty(notes = "分页大小(1~100)")
    @JSONField(name = "PageSize")
    private String pageSize;

    @ApiModelProperty(notes = "分页索引(0~)")
    @JSONField(name = "PageIndex")
    private String pageIndex;

    /*response data*/
    @ApiModelProperty(notes = "居民ID-返回值")
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

    /*@ApiModelProperty(notes = "居民姓名")
    @JSONField(name = "NAME")
    private String name;
    @ApiModelProperty(notes = "居民身份证")
    @JSONField(name = "IDCARD")
    private String idCardNo;
    @ApiModelProperty(notes = "档案号")
    @JSONField(name = "CODE")
    private String fileCode;
    */
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


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getProductCode());
        inputJson.put("NAME", this.getName());
        inputJson.put("Status", this.getStatus());
        inputJson.put("PERSONID", this.getPersonId());
        inputJson.put("IDCARD", this.getIdCardNo());
        inputJson.put("CODE", this.getPersonCode());
        inputJson.put("PERSONTYPE", this.getPersonType());
        inputJson.put("TYPEVALUE", this.getTypeValue());
        inputJson.put("PageSize", this.getPageSize());
        inputJson.put("PageIndex", this.getPageIndex());
        return inputJson.toJSONString();
    }

}
