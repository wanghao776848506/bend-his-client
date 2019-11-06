package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "InspectionReportBO - 检查检验报告", description = "检查检验报告")
public class InspectionReportBO {

    @ApiModelProperty(notes = "科室名称")
    @JSONField(name = "DEPT_NAME")
    private String departmentName;

    @ApiModelProperty(notes = "报告时间")
    @JSONField(name = "EXEC_TIME")
    private String execTime;

    @ApiModelProperty(notes = "金额")
    @JSONField(name = "FEE")
    private String fee;

    @ApiModelProperty(notes = "结论")
    @JSONField(name = "CONCLUSION")
    private String conclusion;

    @ApiModelProperty(notes = "操作员/执行人")
    @JSONField(name = "USER_NAME")
    private String userName;

    @ApiModelProperty(notes = "模板ID")
    @JSONField(name = "TEMPLATE_ID")
    private String templateId;

    @ApiModelProperty(notes = "病人姓名")
    @JSONField(name = "NAME")
    private String name;

    @ApiModelProperty(notes = "单据号")
    @JSONField(name = "CODE")
    private String code;

    @ApiModelProperty(notes = "样本类型")
    @JSONField(name = "SPECIMEN_TYPE")
    private String specimenType;

    @ApiModelProperty(notes = "单据类型")
    @JSONField(name = "BILL_TYPE")
    private String billType;

    @ApiModelProperty(notes = "主诉")
    @JSONField(name = "SUBJECTIVE")
    private String subjective;

    @ApiModelProperty(notes = "性别")
    @JSONField(name = "GENDER")
    private String gender;

    @ApiModelProperty(notes = "包名称")
    @JSONField(name = "PACK_NAME")
    private String packName;

    @ApiModelProperty(notes = "申请单ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "SOURCE")
    private String source;

    @ApiModelProperty(notes = "开单时间")
    @JSONField(name = "CREATE_TIME")
    private String createTime;

    @ApiModelProperty(notes = "传染病异常结果类型")
    @JSONField(name = "INFECTIOUS")
    private String infectious;

    @ApiModelProperty(notes = "备注")
    @JSONField(name = "REMARK")
    private String remark;

    @ApiModelProperty(notes = "结果")
    @JSONField(name = "RESULT")
    private String result;

    @ApiModelProperty(notes = "年龄")
    @JSONField(name = "AGE")
    private String age;

    @ApiModelProperty(notes = "执行人")
    @JSONField(name = "ACTOR_NAME")
    private String actorName;

    @ApiModelProperty(notes = "单据状态")
    @JSONField(name = "BILLSTATUS")
    private String billStatus;

    @ApiModelProperty(notes = "诊断")
    @JSONField(name = "ASSESSMENT")
    private String assessment;

    @ApiModelProperty(notes = "检查检验项目明细")
    private List<InspectionReportItemBO> inspectionReportItemList;

}
