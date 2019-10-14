package com.bend.his.insurance.application.controller.hospital;

import com.bend.common.framework.vo.CommonResult;
import com.bend.his.insurance.api.HospitalOrganizationService;
import com.bend.his.insurance.api.bo.HospitalOrganizationPageBO;
import com.bend.his.insurance.api.dto.HospitalOrganizationDTO;
import com.bend.his.insurance.application.convert.HospitalOrganizationConvert;
import com.bend.his.insurance.application.vo.hospital.HospitalOrganizationPageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.bend.common.framework.vo.CommonResult.success;

@RestController
@RequestMapping("/hospital/organization/")
@Api("医疗机构模块")
public class HospitalOrganizationController {

//    @Reference(validation = "true", version = "${dubbo.provider.HospitalOrganizationService.version}")
    @Autowired
    private HospitalOrganizationService hospitalOrganizationService;


    // 分页
    @GetMapping("/page")
    @ApiOperation(value = "医疗机构分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "hospitalName", value = "医院名称，模糊匹配", example = "小王"),
            @ApiImplicitParam(name = "organizationName", value = "机构名称，模糊匹配", example = "小王"),
            @ApiImplicitParam(name = "pageNo", value = "页码，从 1 开始", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", required = true, example = "10"),
    })
    public CommonResult<HospitalOrganizationPageVO> page(@RequestParam(value = "hospitalName", required = false) String hospitalName,
                                                         @RequestParam(value = "organizationName", required = false) String organizationName,
                                                         @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
                                                         @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        HospitalOrganizationDTO hospitalOrganizationDTO = new HospitalOrganizationDTO()
                .setHospitalName(hospitalName).setOrganizationName(organizationName)
                .setPageNo(pageNo).setPageSize(pageSize);
        // 查询分页
        HospitalOrganizationPageBO result = hospitalOrganizationService.getHospitalOrganizationByPage(hospitalOrganizationDTO);
        // 转换结果
        return success(HospitalOrganizationConvert.INSTANCE.convert(result));
    }


}
