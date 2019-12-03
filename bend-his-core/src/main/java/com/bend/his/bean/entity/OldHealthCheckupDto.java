package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel(value = "OldHealthCheckupDto - 老年人体检查询", description = "老年人体检查询")
public class OldHealthCheckupDto extends AbstractBaseEntity {

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

    /*response data*/
    @ApiModelProperty(notes = "个人ID/居民ID")
    @JSONField(name = "PERSONID")
    private String personId;

    @ApiModelProperty(notes = "老年人随访记录ID/体检ID")
    @JSONField(name = "OLDPEOPLEID")
    private String oldPeopleId;

    @ApiModelProperty(notes = "随访日期")
    @JSONField(name = "FOLLOW_UP_DATE")
    private String followUpDate;

    @ApiModelProperty(notes = "本人联系方式")
    @JSONField(name = "PERSON_TEL")
    private String personTel;

    @ApiModelProperty(notes = "创建时间")
    @JSONField(name = "CREATE_TIME")
    private String createTime;

    @ApiModelProperty(notes = "随访医生")
    @JSONField(name = "DOCTOR_NAME")
    private String doctorName;

    @ApiModelProperty(notes = "操作用户姓名")
    @JSONField(name = "USER_NAME")
    private String userName;

    @ApiModelProperty(notes = "健康评价:1正常2异常")
    @JSONField(name = "ASSESSMENT")
    private String assessment;

    @ApiModelProperty(notes = "健康评价异常信息,使用|分隔")
    @JSONField(name = "ASSESSMENTABNORMAL")
    private String assessmentAbnormal;

    @ApiModelProperty(notes = "年龄")
    @JSONField(name = "AGE")
    private String AGE;

    @ApiModelProperty(notes = "性别")
    @JSONField(name = "GENDER")
    private String GENDER;

    @ApiModelProperty(notes = "身份证号码")
    @JSONField(name = "CARD_ID")
    private String idCardNo;

    @ApiModelProperty(notes = "人员健康档案编号")
    @JSONField(name = "PERSON_CODE")
    private String personCode;

    @ApiModelProperty(notes = "姓名")
    @JSONField(name = "NAME")
    private String name;

    @ApiModelProperty(notes = "健康指导(1是1 2是2 3是4，多选就是相加)：0 无1 纳入慢性病患者健康管理 2 建议复查 3 建议转诊")
    @JSONField(name = "GUIDANCE")
    private String guidance;

    @ApiModelProperty(notes = "是否完善")
    @JSONField(name = "PERFECT")
    private String perfect;

    @ApiModelProperty(notes = "手机号")
    @JSONField(name = "TELPHONE")
    private String telphone;

    @ApiModelProperty(notes = "当前行数")
    @JSONField(name = "R__N")
    private String rowNum;
}
