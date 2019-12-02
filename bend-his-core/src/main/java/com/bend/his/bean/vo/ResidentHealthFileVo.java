package com.bend.his.bean.vo;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ResidentHealthFileVo - 居民个人健康体检记录", description = "居民个人健康体检记录")
public class ResidentHealthFileVo extends BaseEntity {
    @ApiModelProperty(notes = "随访ID", required = true)
    @JSONField(name = "MtID")
    private String mtId;

    @Override
    public String getInputParameter() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getAuthCode());
        inputJson.put("MtID", this.getMtId());
        return inputJson.toJSONString();
    }
}
