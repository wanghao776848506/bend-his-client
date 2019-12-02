package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ApiModel(value = "OrganBO - 脏器功能", description = "脏器功能")
public class OrganBO {

    @ApiModelProperty(notes = "ID")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "口唇：1红润2苍白3 发绀 4 皲裂 5 疱疹")
    @JSONField(name = "Lips")
    private String lips;

    @ApiModelProperty(notes = "齿列： 1正常 2 缺齿 3 龋齿 4义齿(假牙)")
    @JSONField(name = "Dentition")
    private String dentition;

    @ApiModelProperty(notes = "缺齿数字排列")
    @JSONField(name = "MissingTeeth")
    private String missingTeeth;

    @ApiModelProperty(notes = "龋齿数字排列")
    @JSONField(name = "Caries")
    private String caries;

    @ApiModelProperty(notes = "义齿(假牙)数字排列")
    @JSONField(name = "Denture")
    private String denture;

    @ApiModelProperty(notes = "咽部：1 无充血 2 充血 3 淋巴滤泡增生")
    @JSONField(name = "Throat")
    private String throat;

    @ApiModelProperty(notes = "左眼视力")
    @JSONField(name = "LeftEye")
    private String leftEye;

    @ApiModelProperty(notes = "右眼视力")
    @JSONField(name = "RightEye")
    private String rightEye;

    @ApiModelProperty(notes = "左眼纠正视力")
    @JSONField(name = "LeftEyeVc")
    private String leftEyeVc;

    @ApiModelProperty(notes = "右眼纠正视力")
    @JSONField(name = "RightEyeVc")
    private String rightEyeVc;

    @ApiModelProperty(notes = "听力：1 听见 2 听不清或无法听见")
    @JSONField(name = "Hearing")
    private String hearing;

    @ApiModelProperty(notes = "听力：1 听见 2 听不清或无法听见")
    @JSONField(name = "MotorFunction")
    private String motorFunction;

    @ApiModelProperty(notes = "眼底 ：1正常2异常，格式形如： 2|异常情况")
    @JSONField(name = "Fundus")
    private String fundus;

    @ApiModelProperty(notes = "皮肤：1 正常 2 潮红 3 苍白 4 发绀 5 黄染 6 色素沉着 64 其他（内容放【Other】里面）")
    @JSONField(name = "Skin")
    private String skin;

    @ApiModelProperty(notes = "巩膜：1 正常 2 黄染 3 充血 4 其他（内容放【Other】里面）")
    @JSONField(name = "Sclera")
    private String sclera;

    @ApiModelProperty(notes = "淋巴结：1 未触及 2 锁骨上 3 腋窝 4 其他（内容放【Other】里面）")
    @JSONField(name = "LymphNodes")
    private String lymphNodes;

    @ApiModelProperty(notes = "肺 桶状胸：1 否 2 是")
    @JSONField(name = "BarrelChest")
    private String barrelChest;

    @ApiModelProperty(notes = "肺 呼吸音：1正常2异常，格式形如： 2|异常情况")
    @JSONField(name = "BreathSounds")
    private String breathSounds;

    @ApiModelProperty(notes = "肺 罗音：1 无 2 干罗音 3 湿罗音 4 其他（内容放【Other】里面）")
    @JSONField(name = "Rale")
    private String rale;

    @ApiModelProperty(notes = "心脏心律：1 齐 2 不齐 3 绝对不齐")
    @JSONField(name = "Rhythm")
    private String rhythm;

    @ApiModelProperty(notes = "心脏杂音：1 无 2 有 格式形如：1|")
    @JSONField(name = "HeartMurmur")
    private String heartMurmur;

    @ApiModelProperty(notes = "腹部 压痛：1 无 2 有 格式：1|")
    @JSONField(name = "AbdominalTenderness")
    private String abdominalTenderness;

    @ApiModelProperty(notes = "腹部 包块 1无 2有 格式：2|包块内容")
    @JSONField(name = "AbdominalMass")
    private String abdominalMass;

    @ApiModelProperty(notes = "腹部 肝大 1无 2有 格式1|")
    @JSONField(name = "TheAbdomenLiver")
    private String theAbdomenLiver;

    @ApiModelProperty(notes = "腹部 脾大 1无 2有 格式：1|")
    @JSONField(name = "Splenomegaly")
    private String splenomegaly;

    @ApiModelProperty(notes = "腹部 移动性浊音 1无 2有 格式：1|")
    @JSONField(name = "ShiftingDullness")
    private String shiftingDullness;

    @ApiModelProperty(notes = "下肢水肿：1 无 2 单侧 3 双侧不对称 4 双侧对称")
    @JSONField(name = "LowerExtremityEdema")
    private String lowerExtremityEdema;

    @ApiModelProperty(notes = "肛门指诊：1 未及异常 2 触痛 3 包块 4 前列腺异常 5 其他（内容放【Other】里面）")
    @JSONField(name = "Dre")
    private String dre;

    @ApiModelProperty(notes = "乳腺：1 未见异常 2 乳房切除 3 异常泌乳 4 乳腺包块 5 其他（内容放【Other】里面）")
    @JSONField(name = "Breast")
    private String breast;

    @ApiModelProperty(notes = "脏器功能其他")
    @JSONField(name = "OrganOther")
    private String organOther;


    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;


}
