package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResidentDto - 居民信息",description = "居民信息")
public class ResidentDto extends AbstractBaseEntity{

    @ApiModelProperty(notes = "居民姓名")
    @JSONField(name = "NAME")
    private String residentName;

    @ApiModelProperty(notes = "居民身份证")
    @JSONField(name = "IDCARD")
    private String idCardNo;

    @ApiModelProperty(notes = "档案号")
    @JSONField(name = "CODE")
    private String fileCode;

    @ApiModelProperty(notes = "居民ID")
    @JSONField(name = "ID")
    private String residentId;

    @ApiModelProperty(notes = "居民年龄")
    @JSONField(name = "AGE")
    private String residentAge;

    @ApiModelProperty(notes = "出生日期")
    @JSONField(name = "BIRTHDAY")
    private String birthday;

    @ApiModelProperty(notes = "居民性别")
    @JSONField(name = "SEX")
    private String sex;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("NAME", this.getResidentName());
        inputJson.put("IDCARD", this.getIdCardNo());
        inputJson.put("CODE", this.getFileCode());
        return inputJson.toJSONString();
    }

}
