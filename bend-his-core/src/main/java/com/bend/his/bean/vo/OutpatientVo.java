package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 门诊病人
 */
@Data
@ApiModel(value = "OutpatientVo - 门诊病人", description = "门诊病人")
public class OutpatientVo extends BaseEntity {

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "身份证号码")
    @JSONField(name = "身份证号码")
    private String idCardNo;

    @ApiModelProperty(notes = "开始时间")
    @JSONField(name = "开始时间")
    private String beginTime;

    @ApiModelProperty(notes = "结束时间")
    @JSONField(name = "结束时间")
    private String endTime;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("身份证号码", this.getIdCardNo());
        inputJson.put("机构编码", this.getOrganizationCode());
        inputJson.put("开始时间", this.getBeginTime());
        inputJson.put("结束时间", this.getEndTime());
        return inputJson.toJSONString();
    }

}
