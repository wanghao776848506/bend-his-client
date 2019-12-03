package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 医院综合目录查询(科室、医生、病区、床位)
 */
@Data
@ApiModel(value = "ComprehensiveCatalogueDto - 医院综合目录", description = "医院综合目录查询")
public class ComprehensiveCatalogueDto extends AbstractBaseEntity{

    /*response data*/
    @ApiModelProperty(notes = "目录名称")
    @JSONField(name = "目录名称")
    private String directoryName;

    @ApiModelProperty(notes = "目录类型[0科室、1医生、2病区、3床位]", hidden = true)
    @JSONField(name = "目录类型")
    private String directoryType;

    @ApiModelProperty(notes = "目录编码")
    @JSONField(name = "目录编码")
    private String directoryCode;

    @ApiModelProperty(notes = "助记码")
    @JSONField(name = "助记码")
    private String mnemonicCode;

    @ApiModelProperty(notes = "目录类别名称")
    @JSONField(name = "目录类别名称")
    private String directoryCategoryName;

    @ApiModelProperty(notes = "备注[目录类型1： 返回医生所在科室的编码;目录类型3： 返回床位所在的病区编码.]")
    @JSONField(name = "备注")
    private String remark;

    @ApiModelProperty(notes = "病区", hidden = true)
    @JSONField(name = "病区")
    private String inpatientWard;

    @ApiModelProperty(notes = "医生信息")
    private DoctorDto doctorDto;
}
