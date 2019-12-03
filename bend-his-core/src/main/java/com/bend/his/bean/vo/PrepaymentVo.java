package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.PayAccountBO;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 住院预交费
 */
@Data
@ApiModel(value = "PrepaymentVo - 住院预交费", description = "住院预交费")
public class PrepaymentVo extends BaseEntity {

    @ApiModelProperty(notes = "收费人员ID")
    @JSONField(name = "收费人员ID")
    private String userId;

    /*机构编码/机构ID 叫法不一样，数据其实一样*/
    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构ID")
    private String organizationCode;

    @ApiModelProperty(notes = "住院ID/业务ID")
    @JSONField(name = "住院ID")
    private String hospitalizationId;

    @ApiModelProperty(notes = "总金额")
    @JSONField(name = "总金额")
    private String totalFee;

    @ApiModelProperty(notes = "缴费流水号")
    @JSONField(name = "缴费流水号")
    private String paySerialNumber;

    @ApiModelProperty(notes = "缴费方式列表,格式：[{'PaymentID':'支付方式ID','OrgAccID':'账户ID'，'Fee','金额'}]")
    private List<PayAccountBO> paymentList;

    @ApiModelProperty(notes = "缴费方式列表,格式：[{'PaymentID':'支付方式ID','OrgAccID':'账户ID'，'Fee','金额'}]")
    @JSONField(name = "缴费方式列表")
    private String paymentListStr;


    @ApiModelProperty(notes = "厂商编号或厂商唯一标识,退费时会验证，标识不一样不允许退费")
    @JSONField(name = "厂商唯一标识")
    private String manufacturerNumber;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("收费人员ID", this.getUserId());
        inputJson.put("机构ID", this.getOrganizationCode());
        inputJson.put("住院ID", this.getHospitalizationId());
        inputJson.put("总金额", this.getTotalFee());
        inputJson.put("缴费流水号", this.getPaySerialNumber());
        inputJson.put("缴费方式列表", this.getPaymentListStr());
        inputJson.put("厂商唯一标识", this.getManufacturerNumber());
        return inputJson.toJSONString();
    }
}
