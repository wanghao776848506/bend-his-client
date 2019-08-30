package com.bend.his.bean.entity;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 医院三大目录查询(药品、诊疗、耗材)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "医院三大目录查询(药品、诊疗、耗材)")
public class HospitalThreeCatalogueDto extends AbstractBaseEntity{
    /*request params*/
    /*0中药、1、西药、2诊疗、3耗材*/
    @ApiModelProperty(name = "目录类型",notes = "0中药、1、西药、2诊疗、3耗材")
    @JSONField(name = "目录类型",ordinal = 1)
    private String directoryType;

    @ApiModelProperty(name = "目录名称",notes = "目录名称")
    @JSONField(name = "目录名称",ordinal = 2)
    private String directoryName;

    @ApiModelProperty(name = "开始行数",notes = "开始行数")
    @JSONField(name = "开始行数",ordinal = 3)
    private String beginRowNum;

    @ApiModelProperty(name = "结束行数",notes = "结束行数")
    @JSONField(name = "结束行数",ordinal = 4)
    private String endRowNum;

    @ApiModelProperty(name = "开始时间",notes = "开始时间")
    @JSONField(name = "开始时间",ordinal = 5)
    private String beginTime;

    @ApiModelProperty(name = "结束时间",notes = "结束时间")
    @JSONField(name = "结束时间",ordinal = 6)
    private String endTime;

    @ApiModelProperty(name = "机构编码",notes = "机构编码[取接口30返回的ID]")
    @JSONField(name = "机构编码",ordinal = 7)
    private String organizationCode;

    /*response data*/
    @ApiModelProperty(name = "目录编码",notes = "目录编码")
    @JSONField(name = "目录编码")
    private String directoryCode;

    @ApiModelProperty(name = "助记码",notes = "助记码")
    @JSONField(name = "助记码")
    private String mnemonicCode;

    @ApiModelProperty(name = "目录类别名称",notes = "目录类别名称")
    @JSONField(name = "目录类别名称")
    private String directoryCategoryName;

    @ApiModelProperty(name = "单位",notes = "单位")
    @JSONField(name = "单位")
    private String unit;

    @ApiModelProperty(name = "规格",notes = "规格")
    @JSONField(name = "规格")
    private String specification;

    @ApiModelProperty(name = "剂型",notes = "剂型")
    @JSONField(name = "剂型")
    private String formulation;

    @ApiModelProperty(name = "生产厂家名称",notes = "生产厂家名称")
    @JSONField(name = "生产厂家名称")
    private String manufacturerName;

    @ApiModelProperty(name = "创建时间",notes = "创建时间")
    @JSONField(name = "创建时间")
    private String createDate;

    @ApiModelProperty(name = "备注",notes = "备注")
    @JSONField(name = "备注")
    private String remark;

    @ApiModelProperty(notes = "行数")
    @JSONField(name = "行数")
    private String rows;


    @Override
    public String createJSONObject() {
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
