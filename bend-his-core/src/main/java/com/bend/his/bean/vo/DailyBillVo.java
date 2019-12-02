package com.bend.his.bean.vo;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 每日账单/清单
 */
@Getter
@Setter
@ApiModel(value = "DailyBillVo - 每日清单(住院)", description = "每日清单(住院)")
public class DailyBillVo extends BaseEntity {

    /*request data*/
    @ApiModelProperty(notes = "住院ID/业务ID")
    @JSONField(name = "住院ID")
    private String hospitalizationId;

    @ApiModelProperty(notes = "时间/查询日期,[格式:2019-06-02]", example = "2019-06-02")
    @JSONField(name = "时间")
    private String dailyDay;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("住院ID", this.getHospitalizationId());
        inputJson.put("时间", this.getDailyDay());
        return inputJson.toJSONString();
    }
}
