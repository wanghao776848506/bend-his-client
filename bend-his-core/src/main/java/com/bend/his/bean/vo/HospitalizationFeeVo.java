package com.bend.his.bean.vo;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 住院费用明细
 */
@Getter
@Setter
@ApiModel(value = "HospitalizationFeeVo - 住院费用明细查询", description = "住院费用明细查询")
public class HospitalizationFeeVo extends BaseEntity {

    @ApiModelProperty(notes = "住院号")
    @JSONField(name = "住院号")
    private String hospitalizationNo;

    @ApiModelProperty(notes = "业务ID/住院ID")
    @JSONField(name = "业务ID")
    private String businessId;

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
        inputJson.put("业务ID", this.getBusinessId());
        inputJson.put("住院号", this.getHospitalizationNo());
        inputJson.put("开始时间", this.getBeginTime());
        inputJson.put("结束时间", this.getEndTime());
        inputJson.put("状态", this.getState());
        return inputJson.toJSONString();
    }
}
