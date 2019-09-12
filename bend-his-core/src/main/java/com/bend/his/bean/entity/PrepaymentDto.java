package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.PayAccountBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 住院预交费
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PrepaymentDto - 住院预交费", description = "住院预交费")
public class PrepaymentDto extends AbstractBaseEntity {

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

    @ApiModelProperty(notes = "住院总费用/总费用/总金额")
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


    @ApiModelProperty(notes = "厂商编号或厂商唯一标识")
    @JSONField(name = "厂商唯一标识")
    private String manufacturerNumber;

    /*response data*/
    @ApiModelProperty(notes = "ID/住院预交费ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "CODE/缴费流水号")
    @JSONField(name = "CODE")
    private String paySNCode;

    @ApiModelProperty(notes = "SYSCODE/基层流水号")
    @JSONField(name = "SYSCODE")
    private String sysCode;

    @ApiModelProperty(notes = "金额/FEE")
    @JSONField(name = "FEE")
    private BigDecimal fee;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "REMARK")
    private String remark;

    @ApiModelProperty(notes = "收费时间")
    @JSONField(name = "CREATE_TIME")
    private Date createDate;

    @ApiModelProperty(notes = "收费员姓名/操作员")
    @JSONField(name = "NAME")
    private String userName;

    @ApiModelProperty(notes = "缴费时间")
    @JSONField(name = "缴费时间")
    private String payTime;

    @ApiModelProperty(notes = "缴费金额")
    @JSONField(name = "缴费金额")
    private String paymentAmount;


    @Override
    public String createJSONObject() {
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
