package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import com.bend.his.common.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "RegistrationRecordVo - 挂号记录", description = "挂号记录")
public class RegistrationRecordVo extends BaseEntity {

    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "身份证号码")
    @JSONField(name = "身份证号码")
    private String idCardNo;

    @ApiModelProperty(notes = "开始日期,格式:yyyy-MM-dd")
    @JSONField(name = "开始日期")
    private String beginTime;

    @ApiModelProperty(notes = "开始日期,格式:yyyy-MM-dd")
    @JSONField(name = "结束日期")
    private String endTime;


    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("身份证ID", this.getIdCardNo());
        inputJson.put("机构编码", this.getOrganizationCode());
        inputJson.put("开始日期", DateUtil.getDate(this.getBeginTime(), "yyyy-MM-dd"));
        inputJson.put("结束日期", DateUtil.getDate(this.getEndTime(), "yyyy-MM-dd"));
        return inputJson.toJSONString();
    }
}