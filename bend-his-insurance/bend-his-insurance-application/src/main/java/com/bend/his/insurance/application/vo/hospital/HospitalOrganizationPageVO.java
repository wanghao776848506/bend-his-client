package com.bend.his.insurance.application.vo.hospital;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@ApiModel("医疗机构分页 VO")
@Data
@Accessors(chain = true)
public class HospitalOrganizationPageVO implements Serializable {

    @ApiModelProperty(value = "机构总数")
    private Integer total;

    @ApiModelProperty(value = "机构列表")
    private List<HospitalOrganizationVO> list;

}
