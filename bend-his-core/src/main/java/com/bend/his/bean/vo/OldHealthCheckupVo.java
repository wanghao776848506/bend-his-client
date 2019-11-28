package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.AbstractBaseEntity;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 56-8 老年人体检查询
 * <p>
 * "ProductCode": "8CBE1F526BE144419083D25720106D0E",
 * "PageSize":"分页大小(1~100)(必填)",
 * "PageIndex":"分页索引(0~PageSize)(必填)",
 * "RegionCode":"区划编码(必填)",
 * "IsPerfect":"是否完善 0:是 1:否 (不填则查全部)",
 * "FollowUpDateS":"随访开始时间(yyyy-MM-dd)",
 * "FollowUpDateE":"随访结束时间(yyyy-MM-dd)",
 * "NextFollowUpDateS":"下次随访开始时间(yyyy-MM-dd)",
 * "NextFollowUpDateE":"下次随访结束时间(yyyy-MM-dd)",
 * "DoctorID":"随访医生ID"
 * "KeyValueType":"1:姓名或拼音 2:身份证号 3:档案号 (不填则不用填写KeyValue中内容)",
 * "KeyValue":"与KeyValueType绑定内容",
 * "ManageRegionCodeList":"管辖区划列表"
 * <p>
 * -------------------------
 * "OLDPEOPLEID": "体检ID",
 * "PERSONID": "个人ID",
 * "FOLLOW_UP_DATE": "随访日期",
 * "CREATE_TIME": "创建时间",
 * "DOCTOR_NAME": "随访医生姓名",
 * "USER_NAME": "操作员姓名",
 * "ASSESSMENT": "健康评价:1正常2异常",
 * "ASSESSMENTABNORMAL": "健康评价异常内容",
 * "GUIDANCE": "健康指导0无 1 纳入慢性病患者健康管理 2 建议复查 4 建议转诊",
 * "AGE": "年龄",
 * "GENDER": "性别",
 * "CARD_ID": "身份证号",
 * "PERSON_CODE": "个人健康档案编号",
 * "NAME": "姓名",
 * "TELPHONE": "手机号",
 * "PERFECT": "是否完善 0:是 1:否 ",
 * "R__N": "行号"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "OldHealthCheckupVo - 老年人体检查询", description = "老年人体检查询")
public class OldHealthCheckupVo extends BaseEntity {

    @ApiModelProperty(notes = "验证码/产品验证码")
    @JSONField(name = "ProductCode")
    private String productCode;

    @ApiModelProperty(notes = "分页大小(1~100)(必填)")
    @JSONField(name = "PageSize")
    private String pageSize;

    @ApiModelProperty(notes = "分页索引(0~)(必填)")
    @JSONField(name = "PageIndex")
    private String pageIndex;

    @ApiModelProperty(notes = "区划编码(必填)")
    @JSONField(name = "RegionCode")
    private String regionCode;

    @ApiModelProperty(notes = "是否完善 0:是 1:否 (不填则查全部)")
    @JSONField(name = "IsPerfect")
    private String isPerfect;

    @ApiModelProperty(notes = "随访开始时间(yyyy-MM-dd)")
    @JSONField(name = "FollowUpDateS")
    private String followUpDateS;

    @ApiModelProperty(notes = "随访结束时间(yyyy-MM-dd)")
    @JSONField(name = "FollowUpDateE")
    private String followUpDateE;

    @ApiModelProperty(notes = "下次随访开始时间(yyyy-MM-dd)")
    @JSONField(name = "NextFollowUpDateS")
    private String nextFollowUpDateS;

    @ApiModelProperty(notes = "下次随访结束时间(yyyy-MM-dd)")
    @JSONField(name = "NextFollowUpDateE")
    private String nextFollowUpDateE;

    @ApiModelProperty(notes = "随访医生ID")
    @JSONField(name = "DoctorID")
    private String doctorID;

    @ApiModelProperty(notes = "1:姓名或拼音 2:身份证号 3:档案号 (不填则不用填写KeyValue中内容)")
    @JSONField(name = "KeyValueType")
    private String keyValueType;

    @ApiModelProperty(notes = "与KeyValueType绑定内容")
    @JSONField(name = "KeyValue")
    private String keyValue;

    @ApiModelProperty(notes = "管辖区划列表")
    @JSONField(name = "ManageRegionCodeList")
    private String manageRegionCodeList;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getProductCode());
        inputJson.put("RegionCode", this.getRegionCode());
        inputJson.put("PageSize", this.getPageSize());
        inputJson.put("PageIndex", this.getPageIndex());
        inputJson.put("IsPerfect", this.getIsPerfect());
        inputJson.put("FollowUpDateS", this.getFollowUpDateS());
        inputJson.put("FollowUpDateE", this.getFollowUpDateE());
        inputJson.put("NextFollowUpDateS", this.getNextFollowUpDateS());
        inputJson.put("NextFollowUpDateE", this.getNextFollowUpDateE());
        inputJson.put("DoctorID", this.getDoctorID());
        inputJson.put("KeyValueType", this.getKeyValueType());
        inputJson.put("KeyValue", this.getKeyValue());
        inputJson.put("ManageRegionCodeList", this.getManageRegionCodeList());
        return inputJson.toJSONString();
    }
}
