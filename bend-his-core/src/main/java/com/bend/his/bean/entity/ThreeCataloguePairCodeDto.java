package com.bend.his.bean.entity;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.PairCodeBO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 三大目录对码信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ThreeCataloguePairCodeDto - 三大目录对码信息", description = "三大目录对码信息(药品、诊疗、耗材)")
public class ThreeCataloguePairCodeDto extends AbstractBaseEntity {
    /*request params*/
    @ApiModelProperty(notes = "机构编码/机构ID[取接口30返回的ID]")
    @JSONField(name = "机构编码")
    private String organizationCode;

    @ApiModelProperty(notes = "操作人姓名/操作员")
    @JSONField(name = "操作人姓名")
    private String userName;

    @ApiModelProperty(notes = "版本")
    @JSONField(name = "版本")
    private String version;

    @ApiModelProperty(notes = "撤销状态")
    @JSONField(name = "撤销状态")
    private String revocationStatus;

    @ApiModelProperty(notes = "对码详细信息")
    @JSONField(name = "对码详细信息")
    private List<PairCodeBO> pairCodeList;


    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("验证码", this.getAuthCode());
        inputJson.put("机构编码", this.getOrganizationCode());
        inputJson.put("操作人姓名", this.getUserName());
        inputJson.put("版本", this.getVersion());
        inputJson.put("撤销状态", this.getRevocationStatus());
        inputJson.put("对码详细信息", this.getPairCodeList());
        return inputJson.toJSONString();
    }
}
