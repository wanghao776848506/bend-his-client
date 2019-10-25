package com.bend.his.bean.entity;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.bend.his.bean.bo.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "ResidentHealthFileDto - 居民个人健康体检记录", description = "居民个人健康体检记录")
public class ResidentHealthFileDto extends AbstractBaseEntity {
    /*request params*/
    @ApiModelProperty(notes = "验证码/产品验证码")
    @JSONField(name = "ProductCode")
    private String productCode;

    @ApiModelProperty(notes = "随访ID")
    @JSONField(name = "MtID")
    private String mtId;

    /*response data*/
    @ApiModelProperty(notes = "非免疫规划预防接种史")
    @JSONField(name = "vaccList")
    private List<VACCBO> vaccList;

    @ApiModelProperty(notes = "体检主要用药情况")
    @JSONField(name = "drugUseList")
    private List<DrugUseBO> drugUseList;

    @ApiModelProperty(notes = "体检住院治疗情况")
    @JSONField(name = "hosList")
    private List<HospitalTreatmentBO> hosList;

    @ApiModelProperty(notes = "生活方式的职业病危害因素接触史的毒物种类（5种）")
    @JSONField(name = "exLifeOeList")
    private List<OccupationalDiseaseBO> exLifeOeList;

    @ApiModelProperty(notes = "体检主表")
    @JSONField(name = "master")
    private List<ExaminationTableBO> master;

    @ApiModelProperty(notes = "生活方式")
    @JSONField(name = "lifeStyle")
    private List<LifeStyleBO> lifeStyle;

    @ApiModelProperty(notes = "体征")
    @JSONField(name = "body")
    private List<VitalSignBO> body;

    @ApiModelProperty(notes = "脏器功能")
    @JSONField(name = "organ")
    private List<OrganBO> organ;

    @ApiModelProperty(notes = "妇科检查")
    @JSONField(name = "woman")
    private List<WomanBO> woman;

    @ApiModelProperty(notes = "辅助检查")
    @JSONField(name = "labora")
    private List<LaboraBO> labora;

    @ApiModelProperty(notes = "体检中医体质辨识")
    @JSONField(name = "chsCon")
    private List<TCMConstitutionBO> chsCon;

    @ApiModelProperty(notes = "现存主要健康问题")
    @JSONField(name = "problems")
    private List<ProblemBO> problems;

    @ApiModelProperty(notes = "量表筛查得分")
    @JSONField(name = "scaleScore")
    private List<ScaleScoreBO> scaleScore;

    @ApiModelProperty(notes = "扩展信息1")
    @JSONField(name = "ans1")
    private Object ans1;

    @ApiModelProperty(notes = "扩展信息2")
    @JSONField(name = "ans2")
    private Object ans2;

    @ApiModelProperty(notes = "扩展信息3")
    @JSONField(name = "ans3")
    private Object ans3;

    @Override
    public String createJSONObject() {
        JSONObject inputJson = new JSONObject();
        inputJson.put("ProductCode", this.getProductCode());
        inputJson.put("MtID", this.getMtId());
        return inputJson.toJSONString();
    }
}
