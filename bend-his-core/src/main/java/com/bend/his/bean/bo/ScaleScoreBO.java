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
@ApiModel(value = "ScaleScoreBO - 量表筛查得分", description = "量表筛查得分")
public class ScaleScoreBO {

    @ApiModelProperty(notes = "")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "老年人健康状态自我评估： 满意 2 基本满意 3 说不清楚 4 不太满意 5 不满意")
    @JSONField(name = "Health")
    private String health;

    @ApiModelProperty(notes = "生活能力选项：1 可自理（0～3分） 2 轻度依赖（4～8分） 3 中度依赖（9～18分) 4 不能自理（≥19分）")
    @JSONField(name = "LifeSkills")
    private String lifeSkills;

    @ApiModelProperty(notes = "生活能力得分")
    @JSONField(name = "LifeSkillsScore")
    private String lifeSkillsScore;

    @ApiModelProperty(notes = "认知功能选项：1粗筛阴性 2 粗筛阳性，简易智力状态检查")
    @JSONField(name = "CognitiveFunction")
    private String cognitiveFunction;

    @ApiModelProperty(notes = "认知功能得分")
    @JSONField(name = "CognitiveFunctionScore")
    private String cognitiveFunctionScore;

    @ApiModelProperty(notes = "情感状态选项： 粗筛阴性 2 粗筛阳性，老年人抑郁评分检查")
    @JSONField(name = "EmotionalState")
    private String emotionalState;

    @ApiModelProperty(notes = "情感状态得分")
    @JSONField(name = "EmotionalStateScore")
    private String emotionalStateScore;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;
}
