package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel(value = "ResidentBaseInfoVo - 居民健康档案", description = "居民健康档案")
public class ResidentBaseInfoVo extends BaseEntity {

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


    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getAuthCode());
        inputJson.put("RegionCode", this.getRegionCode());
        inputJson.put("NAME", this.getName());
        inputJson.put("Status", this.getStatus());
        inputJson.put("IDCARD", this.getIdCardNo());
        inputJson.put("CODE", this.getPersonCode());
        inputJson.put("PERSONTYPE", this.getPersonType());
        inputJson.put("TYPEVALUE", this.getTypeValue());
        inputJson.put("PageSize", this.getPageSize());
        inputJson.put("PageIndex", this.getPageIndex());
        return inputJson.toJSONString();
    }

}
