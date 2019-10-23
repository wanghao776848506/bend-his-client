package com.bend.his.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 家庭健康史
 "ID": "记录ID",
 "PersonID": "个人ID",
 "RelationshipType": "关系类型:1父亲,2母亲,3兄弟姐妹,4子女",
 "Disease": "疾病:1无(选1就不能选择其它选项),2高血压,4糖尿病,8冠心病,16慢性阻塞性肺疾病,32恶性肿瘤,64脑卒中,128重性精神病,256结核病,512肝炎,1024先天畸形,2048其它",
 "Remark": "其他内容"
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "FamilyHistory - 家庭健康史", description = "家庭健康史")
public class FamilyHistory  implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "记录ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "个人ID")
    @JSONField(name = "PersonID")
    private String personID;

    @ApiModelProperty(notes = "关系类型:1父亲,2母亲,3兄弟姐妹,4子女")
    @JSONField(name = "RelationshipType")
    private String relationshipType;

    @ApiModelProperty(notes = "疾病:1无,2高血压,4糖尿病,8冠心病,16慢性阻塞性肺疾病,32恶性肿瘤,64脑卒中,128重性精神病,256结核病,512肝炎,1024先天畸形,2048其它")
    @JSONField(name = "Disease")
    private String disease;

    @ApiModelProperty(notes = "其他内容")
    @JSONField(name = "Remark")
    private String remark;

}
