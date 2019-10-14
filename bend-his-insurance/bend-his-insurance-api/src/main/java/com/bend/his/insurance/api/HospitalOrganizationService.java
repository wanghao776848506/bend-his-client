package com.bend.his.insurance.api;

import com.bend.his.insurance.api.bo.HospitalOrganizationPageBO;
import com.bend.his.insurance.api.dto.HospitalOrganizationDTO;


public interface HospitalOrganizationService {

    HospitalOrganizationPageBO getHospitalOrganizationByPage(HospitalOrganizationDTO hospitalOrganizationDTO);

    Long addHospitalOrganization();
}
