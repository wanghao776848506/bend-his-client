package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.PayAccountBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 医院机构支付方式
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "HospitalPaymentDto - 医院机构支付方式",description = "医院机构支付方式")
public class HospitalPaymentDto extends AbstractBaseEntity{
    /*request params*/
    /*机构编码/机构ID 叫法不一样，数据其实一样*/
    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构ID")
    private String organizationCode;

    /*response data*/
    @ApiModelProperty(notes = "支付方式ID")
    @JSONField(name = "PaymentID")
    private String paymentId;

    @ApiModelProperty(notes = "支付方式名称")
    @JSONField(name = "Payment")
    private String paymentName;

    @ApiModelProperty(notes = "拼音简写")
    @JSONField(name = "PayPy")
    private String payPy;

//    @ApiModelProperty(notes = "账户列表")
//    @JSONField(name = "OrgAcc")
//    private String orgAcc;

    @ApiModelProperty(notes = "账户列表")
    @JSONField(name = "OrgAcc")
    private List<PayAccountBO> orgAcc;

    @ApiModelProperty(notes = "选中的账户ID")
    @JSONField(name = "selectedAcc")
    private String selectedAccId; //org.tempuri.bean.bo.PayAccountBO.id


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("机构ID", this.getOrganizationCode());
        return inputJson.toJSONString();
    }

}
