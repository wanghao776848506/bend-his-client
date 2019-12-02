package com.bend.his.bean.bo;


import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ApiModel(value = "UserRoleBO - 用户角色列表", description = "用户角色列表")
public class UserRoleBO {

    @ApiModelProperty(notes = "验证码/产品验证码")
    @JSONField(name = "UserRoleID")
    private String userRoleID;
    @ApiModelProperty(notes = "")
    @JSONField(name = "Attr")
    private String attr;
    @ApiModelProperty(notes = "")
    @JSONField(name = "ManageRegionName")
    private String manageRegionName;
    @ApiModelProperty(notes = "电子病历-审核")
    @JSONField(name = "RoleName")
    private String roleName;
    @ApiModelProperty(notes = "")
    @JSONField(name = "RoleID")
    private String roleID;
    @ApiModelProperty(notes = "")
    @JSONField(name = "UserID")
    private String userID;
    @ApiModelProperty(notes = "")
    @JSONField(name = "CurSetManageRegionID")
    private String curSetManageRegionID;
    @ApiModelProperty(notes = "")
    @JSONField(name = "HasThisRoleStr")
    private String hasThisRoleStr;
    @ApiModelProperty(notes = "")
    @JSONField(name = "ManageRegionCode")
    private String manageRegionCode;
    @ApiModelProperty(notes = "")
    @JSONField(name = "ManageRegionID")
    private String manageRegionID;
    @ApiModelProperty(notes = "")
    @JSONField(name = "CurSetManageRegionCode")
    private String curSetManageRegionCode;
    @ApiModelProperty(notes = "")
    @JSONField(name = "CurSetManageRegionName")
    private String curSetManageRegionName;

    @ApiModelProperty(notes = "CurSetManageRegionNameStr")
    @JSONField(name = "CurSetManageRegionNameStr")
    private String curSetManageRegionNameStr;
}
