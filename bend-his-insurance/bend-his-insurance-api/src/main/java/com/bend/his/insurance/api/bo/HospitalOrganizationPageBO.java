package com.bend.his.insurance.api.bo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class HospitalOrganizationPageBO implements Serializable {

    /**
     * 总条数
     */
    private Integer total;

    private List<HospitalOrganizationBO> list;

}
