package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 56-1 查询个人健康体检记录列表
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PersonalHealthCheckupDto - 个人健康体检记录", description = "个人健康体检记录")
public class PersonalHealthCheckupDto extends AbstractBaseEntity {

    @ApiModelProperty(notes = "验证码/产品验证码")
    @JSONField(name = "ProductCode")
    private String productCode;

    @ApiModelProperty(notes = "机构ID/机构编码")
    @JSONField(name = "orgId")
    private String organizationCode;

    @ApiModelProperty(notes = "居民ID")
    @JSONField(name = "PersonID")
    private String personId;

    @ApiModelProperty(notes = "分页大小(1~100)")
    @JSONField(name = "PageSize")
    private String pageSize;

    @ApiModelProperty(notes = "分页索引(0~)")
    @JSONField(name = "PageIndex")
    private String pageIndex;

    /*response data*/
    @ApiModelProperty(notes = "个人ID")
    @JSONField(name = "PERSONID")
    private String id;

    @ApiModelProperty(notes = "随访ID/老年人随访记录ID")
    @JSONField(name = "OLDPEOPLEID")
    private String mtId;

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
    @JSONField(name = "SOPHISTICATION")
    private String sophistication;

    @ApiModelProperty(notes = "机构名称")
    @JSONField(name = "ORGNAME")
    private String organizationName;

    @ApiModelProperty(notes = "当前行数")
    @JSONField(name = "R__N")
    private String rowNum;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getProductCode());
        inputJson.put("PersonID", this.getPersonId());
        inputJson.put("orgId", this.getOrganizationCode());
        inputJson.put("PageSize", this.getPageSize());
        inputJson.put("PageIndex", this.getPageIndex());
        return inputJson.toJSONString();
    }
}
