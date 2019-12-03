package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 机构信息:获取HIS系统中医疗机构的详细信息<br/>
 * 包含：乡镇卫生院和社区服务中心
 */
@Data
@ApiModel(value = "HospitalOrganizationDto - 医院机构信息", description = "医院机构信息")
public class HospitalInfoDto extends AbstractBaseEntity{
    /*response data*/
    @ApiModelProperty(notes = "医院ID")
    @JSONField(name = "ID")
    private String hospitalId;

    @ApiModelProperty(notes = "医院名称")
    @JSONField(name = "医院名称")
    private String hospitalName;

    @ApiModelProperty(notes = "医院地址/地址")
    @JSONField(name = "地址")
    private String hospitalAddr;

    @ApiModelProperty(notes = "联系电话")
    @JSONField(name = "联系电话")
    private String contactPhone;

    @ApiModelProperty(notes = "邮政编码")
    @JSONField(name = "邮政编码")
    private String postalCode;

    @ApiModelProperty(notes = "联系人")
    @JSONField(name = "联系人")
    private String contactPerson;
}
