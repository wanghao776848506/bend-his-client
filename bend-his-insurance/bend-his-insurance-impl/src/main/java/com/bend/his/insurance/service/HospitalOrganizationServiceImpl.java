package com.bend.his.insurance.service;

import com.bend.his.insurance.api.HospitalOrganizationService;
import com.bend.his.insurance.api.bo.HospitalOrganizationPageBO;
import com.bend.his.insurance.api.dto.HospitalOrganizationDTO;
import com.bend.his.insurance.biz.dao.HospitalOrganizationMapper;
import com.bend.his.insurance.biz.dataobject.HospitalOrganizationDO;
import com.bend.his.insurance.convert.HospitalOrganizationConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@org.apache.dubbo.config.annotation.Service(validation = "true", version = "${dubbo.provider.HospitalOrganizationService.version}")
public class HospitalOrganizationServiceImpl implements HospitalOrganizationService {

    @Autowired
    private HospitalOrganizationMapper hospitalOrganizationMapper;

    @Override
    public HospitalOrganizationPageBO getHospitalOrganizationByPage(HospitalOrganizationDTO hospitalOrganizationDTO) {

        HospitalOrganizationPageBO hospitalOrganizationPageBO = new HospitalOrganizationPageBO();
        // 查询分页数据
        int offset = (hospitalOrganizationDTO.getPageNo() - 1) * hospitalOrganizationDTO.getPageSize();
        List<HospitalOrganizationDO> hospitalOrganizationDOList = hospitalOrganizationMapper.selectListByNameLike(
                hospitalOrganizationDTO.getHospitalName(),
                hospitalOrganizationDTO.getOrganizationName(),
                offset, hospitalOrganizationDTO.getPageSize());

        hospitalOrganizationPageBO.setList(HospitalOrganizationConvert.INSTANCE.convert(hospitalOrganizationDOList));

        // 查询分页总数
        hospitalOrganizationPageBO.setTotal(hospitalOrganizationMapper.selectCountByNameLike(hospitalOrganizationDTO.getHospitalName(),
                hospitalOrganizationDTO.getOrganizationName()));
        return hospitalOrganizationPageBO;
    }

    @Override
    public Long addHospitalOrganization() {
        return null;
    }
}
