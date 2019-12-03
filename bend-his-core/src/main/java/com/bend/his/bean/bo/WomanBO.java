package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "WomanBO - 妇科检查", description = "妇科检查")
public class WomanBO {

    @ApiModelProperty(notes = "")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "外阴：1 未见异常 2 异常 格式：1|")
    @JSONField(name = "Vulva")
    private String vulva;

    @ApiModelProperty(notes = "阴道：1 未见异常 2 异常 格式：2|异常情况内容")
    @JSONField(name = "Vaginal")
    private String vaginal;

    @ApiModelProperty(notes = "宫颈：1 未见异常 2 异常 格式：1|")
    @JSONField(name = "Cervix")
    private String cervix;

    @ApiModelProperty(notes = "宫体：1 未见异常 2 异常 格式：1|")
    @JSONField(name = "Palace")
    private String palace;

    @ApiModelProperty(notes = "附件：1 未见异常 2 异常 格式：1|")
    @JSONField(name = "UterineAdnexa")
    private String uterineAdnexa;

    @ApiModelProperty(notes = "阴道分泌物")
    @JSONField(name = "VaginalSecretions")
    private String vaginalSecretions;

    @ApiModelProperty(notes = "梅毒血清学实验")
    @JSONField(name = "Vdrl")
    private String vdrl;

    @ApiModelProperty(notes = "阴道清洁度")
    @JSONField(name = "VaginalCleanness")
    private String vaginalCleanness;

    @ApiModelProperty(notes = "其他")
    @JSONField(name = "Other")
    private String other;

    @ApiModelProperty(notes = "滴虫")
    @JSONField(name = "Trichomonas")
    private String trichomonas;

    @ApiModelProperty(notes = "念珠菌")
    @JSONField(name = "Albicans")
    private String albicans;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;


}
