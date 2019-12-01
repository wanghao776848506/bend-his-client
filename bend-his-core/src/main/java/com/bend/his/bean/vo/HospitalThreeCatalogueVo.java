package com.bend.his.bean.vo;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 医院三大目录查询(药品、诊疗、耗材)
 */
@Getter
@Setter
@ApiModel(value = "HospitalThreeCatalogueVo - 医院三大目录查询(药品、诊疗、耗材)", description = "医院三大目录查询(药品、诊疗、耗材)")
public class HospitalThreeCatalogueVo extends BaseEntity {
    /*request params*/
    @ApiModelProperty(notes = "验证码/授权码")
    @JSONField(name = "验证码")
    protected String authCode;

    /*0中药、1、西药、2诊疗、3耗材*/
    @ApiModelProperty(notes = "目录类型:0中药、1、西药、2诊疗、3耗材")
    @JSONField(name = "目录类型")
    private String directoryType;

    @ApiModelProperty(notes = "目录名称")
    @JSONField(name = "目录名称")
    private String directoryName;

    @ApiModelProperty(notes = "开始行数")
    @JSONField(name = "开始行数")
    private String beginRowNum;

    @ApiModelProperty(notes = "结束行数")
    @JSONField(name = "结束行数")
    private String endRowNum;

    @ApiModelProperty(notes = "开始时间,如：2014-01-01 15:11:22")
    @JSONField(name = "开始时间")
    private String beginTime;

    @ApiModelProperty(notes = "结束时间,如：2014-01-01 23:59:59")
    @JSONField(name = "结束时间")
    private String endTime;

    @ApiModelProperty(notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;


    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("目录类型", this.getDirectoryType());
        inputJson.put("目录名称", this.getDirectoryName());
        inputJson.put("开始行数", this.getBeginRowNum());
        inputJson.put("结束行数", this.getEndRowNum());
        inputJson.put("开始时间", this.getBeginTime());
        inputJson.put("结束时间", this.getEndTime());
        inputJson.put("机构编码", this.getOrganizationCode());
        return inputJson.toJSONString();
    }
}
