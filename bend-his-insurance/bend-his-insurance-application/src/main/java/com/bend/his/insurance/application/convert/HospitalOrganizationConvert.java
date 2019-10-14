package com.bend.his.insurance.application.convert;

import com.bend.his.insurance.api.bo.HospitalOrganizationBO;
import com.bend.his.insurance.api.bo.HospitalOrganizationPageBO;
import com.bend.his.insurance.application.vo.hospital.HospitalOrganizationPageVO;
import com.bend.his.insurance.application.vo.hospital.HospitalOrganizationVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HospitalOrganizationConvert {

    HospitalOrganizationConvert INSTANCE = Mappers.getMapper(HospitalOrganizationConvert.class);

    @Mappings({})
    HospitalOrganizationPageVO convert(HospitalOrganizationPageBO result);

    @Mappings({})
    HospitalOrganizationVO convert(HospitalOrganizationBO result);

}
