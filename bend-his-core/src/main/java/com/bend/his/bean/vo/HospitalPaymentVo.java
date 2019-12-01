package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 医院机构支付方式
 */
@Getter
@Setter
@ApiModel(value = "HospitalPaymentVo - 医院机构支付方式", description = "医院机构支付方式")
public class HospitalPaymentVo extends BaseEntity {
    /*request params*/
    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构ID")
    private String organizationCode;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("机构ID", this.getOrganizationCode());
        return inputJson.toJSONString();
    }

}
