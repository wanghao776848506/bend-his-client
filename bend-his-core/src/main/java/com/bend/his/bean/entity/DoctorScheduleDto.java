package com.bend.his.bean.entity;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 机构、人员的排班信息 -- 主要是医生排班表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DoctorScheduleDto - 医生排班表", description = "医生排班表")
public class DoctorScheduleDto extends AbstractBaseEntity {

    /*机构编码/机构ID 叫法不一样，数据其实一样*/
    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构ID")
    private String organizationCode;

    @ApiModelProperty(notes = "医生ID")
    @JSONField(name = "医生ID")
    private String doctorId;

    @ApiModelProperty(notes = "开始时间/日期")
    @JSONField(name = "开始日期")
    private String beginTime;

    @ApiModelProperty(notes = "结束时间/日期")
    @JSONField(name = "结束日期")
    private String endTime;


    /*response data*/
    @ApiModelProperty(notes = "医生名称")
    @JSONField(name = "医生名称")
    private String doctorName;
    /*@ApiModelProperty(notes = "医生ID")
    @JSONField(name = "医生ID")
    private String doctorId;*/

    @ApiModelProperty(notes = "排班日期")
    @JSONField(name = "排班日期")
    private String scheduleDate;

    @ApiModelProperty(notes = "班次[上午、下午]")
    @JSONField(name = "班次")
    private String scheduleShift;

    @ApiModelProperty(notes = "限制挂号数量")
    @JSONField(name = "限制挂号数量")
    private Integer registerLimitNum;

    @ApiModelProperty(notes = "已挂号数量")
    @JSONField(name = "已挂号数量")
    private Integer registeredNum;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("机构ID", this.getOrganizationCode());
        inputJson.put("医生ID", this.getDoctorId());
        inputJson.put("开始日期", this.getBeginTime());
        inputJson.put("结束日期", this.getEndTime());
        return inputJson.toJSONString();
    }

}
