package com.bend.his.insurance.convert;

import com.bend.his.insurance.api.bo.HospitalOrganizationBO;
import com.bend.his.insurance.biz.dataobject.HospitalOrganizationDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HospitalOrganizationConvert {

    HospitalOrganizationConvert INSTANCE = Mappers.getMapper(HospitalOrganizationConvert.class);

    @Mappings({})
    HospitalOrganizationBO convert(HospitalOrganizationDO hospitalOrganizationDO);

    @Mappings({})
    List<HospitalOrganizationBO> convert(List<HospitalOrganizationDO> hospitalOrganizations);

}
