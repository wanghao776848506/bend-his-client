package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 住院病人
 */
@Getter
@Setter
@ApiModel(value = "InpatientVo - 住院病人信息", description = "住院病人信息")
public class InpatientVo extends BaseEntity {

    /*机构编码/机构ID 叫法不一样，数据其实一样*/
    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "身份证号码")
    @JSONField(name = "身份证号码")
    private String idCardNo;

    @ApiModelProperty(notes = "开始时间,格式:yyyy-MM-dd hh:mm:ss")
    @JSONField(name = "开始时间")
    private String beginTime;

    @ApiModelProperty(notes = "结束时间,格式:yyyy-MM-dd hh:mm:ss")
    @JSONField(name = "结束时间")
    private String endTime;

    @ApiModelProperty(notes = "状态[0表示清单不包含退药、退费等产生的负数记录；1表示清单包含退药、退费等产生的负数记录。（非必填项：默认为0 ）]")
    @JSONField(name = "状态")
    private Integer state;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("身份证号码", this.getIdCardNo());
        inputJson.put("机构编码", this.getOrganizationCode());
        inputJson.put("开始时间", this.getBeginTime());
        inputJson.put("结束时间", this.getEndTime());
        inputJson.put("状态", this.getState());
        return inputJson.toJSONString();
    }
}
