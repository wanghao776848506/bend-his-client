package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.UserRoleBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
@ApiModel(value = "PublicAuthDto - 公卫登录认证数据", description = "公卫登录认证数据")
public class PublicAuthDto extends AbstractBaseEntity {
    /*response data*/
    @ApiModelProperty(notes = "职员的PersonID/用户ID")
    @JSONField(name = "UserId")
    private String userId;

    @ApiModelProperty(notes = "机构的名称")
    @JSONField(name = "OrgName")
    private String orgName;

    @ApiModelProperty(notes = "机构ID")
    @JSONField(name = "OrgId")
    private String orgId;

    @ApiModelProperty(notes = "机构的类型")
    @JSONField(name = "OrgType")
    private String orgType;

    @ApiModelProperty(notes = "管辖机构列表")
    @JSONField(name = "RegionCodeList")
    private List<String> regionCodeList;

    @ApiModelProperty(notes = "医务人员ID")
    @JSONField(name = "EmployeeID")
    private String employeeID;

    @ApiModelProperty(notes = "")
    @JSONField(name = "UserRoleList")
    private List<UserRoleBO> userRoleList;

}
