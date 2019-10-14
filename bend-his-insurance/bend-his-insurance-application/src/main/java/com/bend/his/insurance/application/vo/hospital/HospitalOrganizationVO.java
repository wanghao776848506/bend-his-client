package com.bend.his.insurance.application.vo.hospital;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@ApiModel("医疗机构 VO")
@Data
@Accessors(chain = true)
public class HospitalOrganizationVO implements Serializable {

    private Long id;

    @ApiModelProperty(value = "医院名称", required = false, example = "")
    private String hospitalName;

    @ApiModelProperty(value = "机构名称", required = false, example = "")
    private String organizationName;

    @ApiModelProperty(value = "医院ID")
    private String hospitalId;

    @ApiModelProperty(value = "医院地址")
    private String hospitalAddr;

    @ApiModelProperty(value = "联系人")
    private String contactPerson;

    @ApiModelProperty(value = "联系电话")
    private String contactPhone;

    @ApiModelProperty(value = "邮政编码")
    private String postalCode;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "删除标记[0:默认,1:删除]")
    private String isDelete;

    @ApiModelProperty(value = "删除时间")
    private Date deleteTime;

    @ApiModelProperty(value = "操作员ID-[创建]")
    private String createUserId;

    @ApiModelProperty(value = "操作员ID-[删除]")
    private String deleteUserId;
}
