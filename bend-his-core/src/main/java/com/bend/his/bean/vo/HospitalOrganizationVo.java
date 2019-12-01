package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 机构信息:获取HIS系统中医疗机构的详细信息<br/>
 * 包含：乡镇卫生院和社区服务中心
 */
@Getter
@Setter
@ApiModel(value = "HospitalOrganizationVo - 机构信息", description = "机构信息")
public class HospitalOrganizationVo extends BaseEntity {
    /*request params*/
    @ApiModelProperty(notes = "机构名称")
    @JSONField(name = "机构名称")
    private String organizationName;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("机构名称", this.getOrganizationName());
        return inputJson.toJSONString();
    }
}
