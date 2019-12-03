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
@ApiModel(value = "HospitalOrganizationDto - 机构信息", description = "机构信息")
public class HospitalOrganizationDto extends AbstractBaseEntity {
    /*response data*/
    @ApiModelProperty(notes = "机构编码/机构ID")
    @JSONField(name = "机构ID")
    private String organizationCode;

    @ApiModelProperty(notes = "机构名称")
    @JSONField(name = "机构名称")
    private String orgName;

    @ApiModelProperty(notes = "机构地址/地址")
    @JSONField(name = "机构地址")
    private String orgAddr;

    @ApiModelProperty(notes = "联系电话")
    @JSONField(name = "联系电话")
    private String contactPhone;

    @ApiModelProperty(notes = "机构等级名称")
    @JSONField(name = "机构等级")
    private String organizationTypeName;
}
