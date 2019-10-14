package com.bend.his.insurance.biz.dao;

import com.bend.his.insurance.biz.dataobject.HospitalOrganizationDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalOrganizationMapper {


    List<HospitalOrganizationDO> selectListByNameLike(@Param("hospitalName") String hospitalName,
                                                      @Param("organizationName") String organizationName,
                                                      @Param("offset") Integer offset,
                                                      @Param("limit") Integer limit);

    Integer selectCountByNameLike(@Param("hospitalName") String hospitalName,
                                  @Param("organizationName") String organizationName);
}
