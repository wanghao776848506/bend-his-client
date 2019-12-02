package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "OccupationalDiseaseBO - 生活方式的职业病危害因素接触史的毒物种类", description = "生活方式的职业病危害因素接触史的毒物种类")
public class OccupationalDiseaseBO {

    @ApiModelProperty(notes = "ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "生活方式id")
    @JSONField(name = "LifestyleID")
    private String lifestyleID;

    @ApiModelProperty(notes = "毒物种类编号Integer：0粉尘 1放射物质 2 物理因素 3化学物质 4其他")
    @JSONField(name = "PoisonKind")
    private String poisonKind;

    @ApiModelProperty(notes = "毒物名称")
    @JSONField(name = "PoisonName")
    private String poisonName;

    @ApiModelProperty(notes = "是否有防护措施：0未选 1无 2 有")
    @JSONField(name = "IsProtection")
    private String isProtection;

    @ApiModelProperty(notes = "防护措施")
    @JSONField(name = "ProtectionMeasures")
    private String protectionMeasures;

    @ApiModelProperty(notes = "某些“其他或异常”字段的内容,例如：症状字段的其他内容为“感冒”，皮肤字段的其他内容为“其他皮肤")
    @JSONField(name = "otherText")
    private String otherText;


}
