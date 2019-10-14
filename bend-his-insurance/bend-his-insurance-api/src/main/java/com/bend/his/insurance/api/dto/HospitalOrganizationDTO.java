package com.bend.his.insurance.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;


@Data
@Accessors(chain = true)
public class HospitalOrganizationDTO {

    /**
     * 医院名称
     * <p>
     * 模糊查询
     */
    private String hospitalName;
    /**
     * 机构名称
     * <p>
     * 模糊查询
     */
    private String organizationName;

    @NotNull(message = "页码不能为空")
    private Integer pageNo;
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize;

}
