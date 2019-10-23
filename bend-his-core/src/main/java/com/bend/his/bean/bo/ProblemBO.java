package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ProblemBO - 现存主要健康问题", description = "现存主要健康问题")
public class ProblemBO {

    @ApiModelProperty(notes = "")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "脑血管疾病：1 未发现 2 缺血性卒中 3 脑出血 4 蛛网膜下腔出血 5 短暂性脑缺血发作 6 其他(内容放【Other】里面)")
    @JSONField(name = "Cerebrovascular")
    private String cerebrovascular;

    @ApiModelProperty(notes = "肾脏疾病：1 未发现 2 糖尿病肾病 3 肾功能衰竭 4 急性肾炎 5 慢性肾炎 6 其他(内容放【Other】里面")
    @JSONField(name = "Kidney")
    private String kidney;

    @ApiModelProperty(notes = "心脏疾病：1 未发现 2 心肌梗死 3 心绞痛 4 冠状动脉血运重建 5 充血性心力衰竭 6 心前区疼痛 7 其他(内容放【Other】里面)")
    @JSONField(name = "Heart")
    private String heart;

    @ApiModelProperty(notes = "血管疾病: 1 未发现 2 夹层动脉瘤 3 动脉闭塞性疾病 4 其他(内容放【Other】里面)")
    @JSONField(name = "Vascular")
    private String vascular;

    @ApiModelProperty(notes = "眼部疾病:1 未发现 2 视网膜出血或渗出 3 视乳头水肿 4 白内障 5 其他(内容放【Other】里面)")
    @JSONField(name = "Eyes")
    private String eyes;

    @ApiModelProperty(notes = "神经系统疾病:1 未发现 2 有(内容放【Other】里面)")
    @JSONField(name = "Nervoussystems")
    private String nervoussystems;

    @ApiModelProperty(notes = "其他系统疾病:1 未发现 2 有(内容放【Other】里面)")
    @JSONField(name = "Others")
    private String others;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;

}
