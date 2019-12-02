package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 56-1 查询个人健康体检记录列表
 */
@Getter
@Setter
@ApiModel(value = "PersonalHealthCheckupVo - 个人健康体检记录", description = "个人健康体检记录")
public class PersonalHealthCheckupVo extends BaseEntity {

    @ApiModelProperty(notes = "机构ID/机构编码")
    @JSONField(name = "orgId")
    private String organizationCode;

    @ApiModelProperty(notes = "居民ID")
    @JSONField(name = "PersonID")
    private String personId;

    @ApiModelProperty(notes = "分页大小(1~100)")
    @JSONField(name = "PageSize")
    private String pageSize;

    @ApiModelProperty(notes = "分页索引(0~)")
    @JSONField(name = "PageIndex")
    private String pageIndex;


    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getAuthCode());
        inputJson.put("PersonID", this.getPersonId());
        inputJson.put("orgId", this.getOrganizationCode());
        inputJson.put("PageSize", this.getPageSize());
        inputJson.put("PageIndex", this.getPageIndex());
        return inputJson.toJSONString();
    }
}
