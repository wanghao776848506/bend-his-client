package com.bend.his.bean.entity;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 每日账单/清单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DailyBillDto - 每日清单(住院)", description = "每日清单(住院)")
public class DailyBillDto extends AbstractBaseEntity {

    /*request data*/
    @ApiModelProperty(notes = "住院ID/业务ID")
    @JSONField(name = "住院ID")
    private String hospitalizationId;

    @ApiModelProperty(notes = "时间/查询日期,[格式:2019-06-02]",example = "2019-06-02")
    @JSONField(name = "时间")
    private String dailyDay;

    /*response data*/

    @ApiModelProperty(notes = "每日清单ID")
    @JSONField(name = "ID")
    private String dailyBillId;

    /*@ApiModelProperty(notes = "时间/查询日期")
    @JSONField(name = "时间")
    private String dailyDay;*/

    @ApiModelProperty(notes = "项目名称[费用]")
    @JSONField(name = "项目名称")
    private String costItemName;

    @ApiModelProperty(notes = "数量")
    @JSONField(name = "数量")
    private Double quantity;

    @ApiModelProperty(notes = "金额/小计/Fee")
    @JSONField(name = "金额")
    private BigDecimal amount;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("住院ID", this.getHospitalizationId());
        inputJson.put("时间", this.getDailyDay());
        return inputJson.toJSONString();
    }
}
