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
@ApiModel(value = "LifeStyleBO - 生活方式", description = "生活方式")
public class LifeStyleBO {

    @ApiModelProperty(notes = "ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "锻炼频率")
    @JSONField(name = "ExerciseFrequency")
    private String exerciseFrequency;

    @ApiModelProperty(notes = "每次锻炼时间")
    @JSONField(name = "EachExerciseTime")
    private String eachExerciseTime;

    @ApiModelProperty(notes = "坚持锻炼时间")
    @JSONField(name = "ExerciseTime")
    private String exerciseTime;

    @ApiModelProperty(notes = "锻炼方式")
    @JSONField(name = "ExerciseMethod")
    private String exerciseMethod;

    @ApiModelProperty(notes = "每周锻炼次数")
    @JSONField(name = "ExerciseWeekTimes")
    private String exerciseWeekTimes;

    @ApiModelProperty(notes = "饮食习惯：1 荤素均衡 2 荤食为主 3 素食为主 4 嗜盐 5 嗜油 6 嗜糖")
    @JSONField(name = "Diet")
    private String diet;

    @ApiModelProperty(notes = "吸烟状况")
    @JSONField(name = "SmokingStatus")
    private String smokingStatus;

    @ApiModelProperty(notes = "日吸烟量")
    @JSONField(name = "Smoking")
    private String smoking;

    @ApiModelProperty(notes = "开始吸烟年龄")
    @JSONField(name = "SmokingAge")
    private String smokingAge;

    @ApiModelProperty(notes = "戒烟年龄")
    @JSONField(name = "AgeQuit")
    private String ageQuit;

    @ApiModelProperty(notes = "饮酒频率")
    @JSONField(name = "DrinkingFrequency")
    private String drinkingFrequency;

    @ApiModelProperty(notes = "日饮酒量")
    @JSONField(name = "DailyAlcoholIntake")
    private String dailyAlcoholIntake;

    @ApiModelProperty(notes = "是否戒酒：1未接 2已戒")
    @JSONField(name = "IsAlcohol")
    private String isAlcohol;

    @ApiModelProperty(notes = "戒酒年龄")
    @JSONField(name = "AlcoholAge")
    private String alcoholAge;

    @ApiModelProperty(notes = "开始饮酒年龄")
    @JSONField(name = "AgeStartedDrinking")
    private String ageStartedDrinking;

    @ApiModelProperty(notes = "近一年内是否曾醉酒：1是 2否")
    @JSONField(name = "IsDrunkLastYear")
    private String isDrunkLastYear;

    @ApiModelProperty(notes = "饮酒种类")
    @JSONField(name = "AlcoholType")
    private String alcoholType;

    @ApiModelProperty(notes = "是否职业暴露")
    @JSONField(name = "IsOe")
    private String isOe;

    @ApiModelProperty(notes = "工种")
    @JSONField(name = "Occupation")
    private String occupation;

    @ApiModelProperty(notes = "从业时间")
    @JSONField(name = "WorkingTime")
    private String workingTime;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;

}
