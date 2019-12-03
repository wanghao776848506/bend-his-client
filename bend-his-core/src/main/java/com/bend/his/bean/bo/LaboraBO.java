package com.bend.his.bean.bo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "LaboraBO - 辅助检查", description = "辅助检查")
public class LaboraBO {

    @ApiModelProperty(notes = "")
    @JSONField(name = "ID")
    private String id;

    @ApiModelProperty(notes = "空腹血糖")
    @JSONField(name = "FastingBloodGlucose")
    private String fastingBloodGlucose;

    @ApiModelProperty(notes = "餐后血糖")
    @JSONField(name = "PostprandialBloodGlucose")
    private String postprandialBloodGlucose;

    @ApiModelProperty(notes = "随机血糖")
    @JSONField(name = "RandomBloodGlucose")
    private String randomBloodGlucose;

    @ApiModelProperty(notes = "血红蛋白")
    @JSONField(name = "Hemoglobin")
    private String hemoglobin;

    @ApiModelProperty(notes = "白细胞")
    @JSONField(name = "Leukocyte")
    private String leukocyte;

    @ApiModelProperty(notes = "血小板")
    @JSONField(name = "Platelet")
    private String platelet;

    @ApiModelProperty(notes = "血常规其他")
    @JSONField(name = "OtherBlood")
    private String otherBlood;

    @ApiModelProperty(notes = "尿蛋白")
    @JSONField(name = "UrineProtein")
    private String urineProtein;

    @ApiModelProperty(notes = "尿糖")
    @JSONField(name = "Urine")
    private String urine;

    @ApiModelProperty(notes = "尿酮体")
    @JSONField(name = "Ketone")
    private String ketone;

    @ApiModelProperty(notes = "尿潜血")
    @JSONField(name = "OccultBloodInUrine")
    private String occultBloodInUrine;

    @ApiModelProperty(notes = "尿常规其他")
    @JSONField(name = "OtherUrine")
    private String otherUrine;

    @ApiModelProperty(notes = "尿微量白蛋白")
    @JSONField(name = "UrinaryAlbumin")
    private String urinaryAlbumin;

    @ApiModelProperty(notes = "大便潜血")
    @JSONField(name = "FecalOccultBlood")
    private String fecalOccultBlood;

    @ApiModelProperty(notes = "血清谷丙转氨酶")
    @JSONField(name = "SerumAla")
    private String serumAla;

    @ApiModelProperty(notes = "血清谷草转氨酶")
    @JSONField(name = "SerumAa")
    private String serumAa;

    @ApiModelProperty(notes = "白蛋白")
    @JSONField(name = "Albumin")
    private String albumin;

    @ApiModelProperty(notes = "总胆红素")
    @JSONField(name = "TotalBilirubin")
    private String totalBilirubin;

    @ApiModelProperty(notes = "结合胆红素")
    @JSONField(name = "Bilirubin")
    private String bilirubin;

    @ApiModelProperty(notes = "血清肌酐")
    @JSONField(name = "SerumCreatinine")
    private String serumCreatinine;

    @ApiModelProperty(notes = "血尿素氮")
    @JSONField(name = "Bun")
    private String bun;

    @ApiModelProperty(notes = "血钾浓度")
    @JSONField(name = "PotassiumConcentration")
    private String potassiumConcentration;

    @ApiModelProperty(notes = "血钠浓度")
    @JSONField(name = "SodiumConcentration")
    private String sodiumConcentration;

    @ApiModelProperty(notes = "总胆固醇")
    @JSONField(name = "TotalCholesterol")
    private String totalCholesterol;

    @ApiModelProperty(notes = "甘油三酯")
    @JSONField(name = "Triglycerides")
    private String triglycerides;

    @ApiModelProperty(notes = "GPT")
    @JSONField(name = "GPT")
    private String gpt;

    @ApiModelProperty(notes = "血清低密度脂蛋白胆固醇")
    @JSONField(name = "LdlCholesterol")
    private String ldlCholesterol;

    @ApiModelProperty(notes = "血清高密度脂蛋白胆固醇")
    @JSONField(name = "HdlCholesterol")
    private String hdlCholesterol;

    @ApiModelProperty(notes = "糖化血红蛋白")
    @JSONField(name = "GlycatedHemoglobin")
    private String glycatedHemoglobin;

    @ApiModelProperty(notes = "乙型肝炎表面抗原")
    @JSONField(name = "HepatitisBSurfaceAntigen")
    private String hepatitisBSurfaceAntigen;

    @ApiModelProperty(notes = "心电图: 1 正常 2 异常 格式：1|")
    @JSONField(name = "Ecg")
    private String ecg;

    @ApiModelProperty(notes = "胸部X线片 1正常2异常 格式：2|胸部太大")
    @JSONField(name = "ChestXRay")
    private String chestXRay;

    @ApiModelProperty(notes = "B超1正常2异常 格式：1|")
    @JSONField(name = "BUltrasonicWave")
    private String bultrasonicWave;

    @ApiModelProperty(notes = "宫颈涂片0未选择1正常2异常 格式： 0|")
    @JSONField(name = "CervicalSmear")
    private String cervicalSmear;

    @ApiModelProperty(notes = "辅助检查其他")
    @JSONField(name = "OtherLaboratory")
    private String otherLaboratory;

    @ApiModelProperty(notes = "红细胞")
    @JSONField(name = "Erythrocytes")
    private String erythrocytes;

    @ApiModelProperty(notes = "白细胞分类计数")
    @JSONField(name = "DifferentialCount")
    private String differentialCount;

    @ApiModelProperty(notes = "血转氨酶")
    @JSONField(name = "BloodTransaminase")
    private String bloodTransaminase;

    @ApiModelProperty(notes = "尿比重")
    @JSONField(name = "Sg")
    private String sg;

    @ApiModelProperty(notes = "尿酸碱度")
    @JSONField(name = "Ph")
    private String ph;

    @ApiModelProperty(notes = "淋球菌")
    @JSONField(name = "Ng")
    private String ng;

    @ApiModelProperty(notes = "梅毒螺旋体抗体")
    @JSONField(name = "Tppa")
    private String tppa;

    @ApiModelProperty(notes = "HIV抗体")
    @JSONField(name = "Hiv")
    private String hiv;

    @ApiModelProperty(notes = "体检时间")
    @JSONField(name = "ExamDate")
    private String examDate;

    @ApiModelProperty(notes = "数据来源")
    @JSONField(name = "Source")
    private String source;

}
