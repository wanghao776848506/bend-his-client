package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 住院病人记录查询
 */
@Getter
@Setter
@ApiModel(value = "InpatientRecordVo - 住院病人记录查询", description = "住院病人记录查询")
public class InpatientRecordVo extends BaseEntity {

    /*机构编码/机构ID 叫法不一样，数据其实一样*/
    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "身份证号码/身份证ID")
    @JSONField(name = "身份证ID")
    private String idCardNo;

    @ApiModelProperty(notes = "病人姓名")
    @JSONField(name = "姓名")
    private String patientName;

    @ApiModelProperty(notes = "是否医保登记[0否1是，当值为1时只取医保已经登记的]")
    @JSONField(name = "是否医保登记")
    private Integer isInsurance;


    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("身份证ID", this.getIdCardNo());
        inputJson.put("姓名", this.getPatientName());
        inputJson.put("是否医保登记", this.getIsInsurance());
        inputJson.put("机构编码", this.getOrganizationCode());
        return inputJson.toJSONString();
    }
}
