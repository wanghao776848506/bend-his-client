package com.bend.his.web.controller;

import com.bend.his.bean.bo.PayAccountBO;
import com.bend.his.bean.entity.*;
import com.bend.his.bean.vo.*;
import com.bend.his.common.CommonPojo;
import com.bend.his.common.DateUtil;
import com.bend.his.common.GenericResponse;
import com.bend.his.common.ResponseFormat;
import com.bend.his.constant.TradeCode;
import com.bend.his.exception.HisException;
import com.bend.his.service.HisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(description = "HIS接口", value = "", tags = {"HIS接口"})
@RestController
public class HISController {

    @Resource
    private HisService hisService;

    /**
     * 登录验证
     *
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "100 接口测试", position = 100, notes = "此接口用于检测接口客户端是否与HIS系统中心服务器相连通")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商编号")
    })
    @PostMapping("his/connector")
    public GenericResponse getHISAuthConnector(@RequestBody CommonPojo<AuthenticationVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            AuthenticationVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            String hisAuthConnector = hisService.getHISAuthConnector(commonPojo);
            return ResponseFormat.retInfo(hisAuthConnector);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 登录验证
     *
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "01 登录验证(弃用，使用公卫的-01登录验证)", position = 1, notes = "此接口用于医院用户登录医保报账客户端的安全验证，用户名与密码由HIS系统统一分配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_01),
            @ApiImplicitParam(name = "memberName", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商编号")
    })
    @PostMapping("his/auth")
    @Deprecated
    public GenericResponse getHISAuth(@RequestBody CommonPojo<AuthenticationVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            AuthenticationVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            AuthenticationDto authenticationDto = hisService.getHISAuth(commonPojo);
            return ResponseFormat.retInfo(authenticationDto);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 03 医院综合目录查询
     *
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "03 医院综合目录查询(科室、医生、病区、床位)", position = 3, notes = "此接口用于获取HIS系统中科室、医师、病区、床位的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_03),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "directoryType", value = "目录类型[0科室、1医生、2病区、3床位]"),
            @ApiImplicitParam(name = "directoryName", value = "目录名称")
    })
    @PostMapping("his/comprehensive/catalogue")
    public GenericResponse getHISComprehensiveCatalogue(@RequestBody CommonPojo<ComprehensiveCatalogueVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            ComprehensiveCatalogueVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<ComprehensiveCatalogueDto> list = hisService.getHISComprehensiveCatalogue(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 备注[目录类型1： 返回医生所在科室的编码;目录类型3： 返回床位所在的病区编码.]
     */
    @ApiOperation(value = "根据医生名称查询", position = 20, notes = "此接口用于获取HIS系统医生的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_03),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "directoryType", value = "目录类型[0科室、1医生、2病区、3床位]"),
            @ApiImplicitParam(name = "directoryName", value = "目录名称[医生名称]")
    })
    @PostMapping("his/hospital/doctor/info")
    public GenericResponse getHISDoctorInfo(@RequestBody CommonPojo<ComprehensiveCatalogueVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            ComprehensiveCatalogueVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<ComprehensiveCatalogueDto> list = hisService.getHISComprehensiveCatalogueByDoctorInfo(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 05 医院三大目录查询
     *
     * @throws HisException
     */
    @ApiOperation(value = "05 医院三大目录查询(药品、诊疗、耗材)", position = 4, notes = "此接口用于获取HIS系统中药品、诊疗、耗材三大目录的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_05),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "directoryType", value = "目录类型[0中药、1、西药、2诊疗、3耗材]"),
            @ApiImplicitParam(name = "directoryName", value = "目录名称"),
            @ApiImplicitParam(name = "beginRowNum", value = "开始行数"),
            @ApiImplicitParam(name = "endRowNum", value = "结束行数"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]")
    })
    @PostMapping("his/hospital/three/catalogue")
    public GenericResponse getHISHospitalThreeCatalogue(@RequestBody CommonPojo<HospitalThreeCatalogueVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            HospitalThreeCatalogueVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<HospitalThreeCatalogueDto> list = hisService.getHISHospitalThreeCatalogue(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }


    /**
     * 06 医院三大目录行数(药品、诊疗、耗材)
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "06 医院三大目录行数(药品、诊疗、耗材)", position = 5, notes = "此接口用于获取HIS系统中药品、诊疗、耗材三大目录的行数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_06),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "directoryType", value = "目录类型[0中药、1、西药、2诊疗、3耗材]"),
            @ApiImplicitParam(name = "directoryName", value = "目录名称"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]")
    })
    @PostMapping("his/hospital/three/catalogue/rows")
    public GenericResponse getHISHospitalThreeCatalogueRows(@RequestBody CommonPojo<HospitalThreeCatalogueVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            HospitalThreeCatalogueVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<HospitalThreeCatalogueDto> list = hisService.getHISHospitalThreeCatalogueRows(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * ICD10数据查询
     */
    @ApiOperation(value = "07 ICD10数据查询", position = 6, notes = "此接口用于获取HIS系统中ICD10的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_07),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "diseaseName", value = "病种名称"),
            @ApiImplicitParam(name = "diseaseCategory", value = "疾病类别"),
            @ApiImplicitParam(name = "beginRowNum", value = "开始行数"),
            @ApiImplicitParam(name = "endRowNum", value = "结束行数"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间")
    })
    @PostMapping("his/hospital/icd10/list")
    public GenericResponse getHISHospitalICD10(@RequestBody CommonPojo<ICD10Vo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            ICD10Vo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<ICD10Dto> list = hisService.getHISHospitalICD10(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 08 ICD10数据行数
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "08 ICD10数据行数 ", position = 7, notes = "此接口用于获取HIS系统中ICD10数据的行数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_08),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "diseaseName", value = "病种名称"),
            @ApiImplicitParam(name = "diseaseCategory", value = "疾病类别"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间")
    })
    @PostMapping("his/hospital/icd10/rows")
    public GenericResponse getHISHospitalICD10Rows(@RequestBody CommonPojo<ICD10Vo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            ICD10Vo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<ICD10Dto> list = hisService.getHISHospitalICD10Rows(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 10 住院病人信息查询
     *
     * @throws HisException
     */
    @ApiOperation(value = "10 住院病人信息查询", position = 8, notes = "此接口用于获取HIS系统中住院病人的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_10),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "idCardNo", value = "身份证号码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "state", value = "状态"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间")
    })
    @PostMapping("his/hospital/inpatient/list")
    public GenericResponse getHISInpatientList(@RequestBody CommonPojo<InpatientVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            InpatientVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<InpatientDto> list = hisService.getHISInpatientList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "12 门诊病人信息查询", position = 9, notes = "此接口用于获取HIS系统中住院病人的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_12),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "idCardNo", value = "身份证号码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间")
    })
    @PostMapping("his/hospital/outpatient/list")
    public GenericResponse getHISOutpatientList(@RequestBody CommonPojo<OutpatientVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            OutpatientVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<OutpatientDto> list = hisService.getHISOutpatientList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 13 中途结算记录查询
     */
    @ApiOperation(value = "13 中途结算记录查询", position = 10, notes = "此接口用于获取HIS系统中住院病人的结算信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_13),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID/住院ID"),
            @ApiImplicitParam(name = "hospitalizationNo", value = "住院号")
    })
    @PostMapping("his/hospital/halfway/settlement/list")
    public GenericResponse getHISHospitalizationSettlement(@RequestBody CommonPojo<HospitalizationMidwaySettlementVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            HospitalizationMidwaySettlementVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<HospitalizationMidwaySettlementDto> list = hisService.getHISHospitalizationSettlement(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 14 住院费用明细查询
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "14 住院费用明细查询", position = 11, notes = "此接口用于获取HIS系统中住院费用明细的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_14),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID"),
            @ApiImplicitParam(name = "hospitalizationNo", value = "住院号"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间"),
            @ApiImplicitParam(name = "state", value = "状态")
    })
    @PostMapping("his/hospital/hospitalization/fee/detail")
    public GenericResponse getHISHospitalizationFee(@RequestBody CommonPojo<HospitalizationFeeVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            HospitalizationFeeVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<HospitalizationFeeDto> list = hisService.getHISHospitalizationFee(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 16 门诊费用明细查询
     */
    @ApiOperation(value = "16 门诊费用明细查询", position = 12, notes = "此接口用于获取HIS系统中门诊费用明细的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_16),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID"),
            @ApiImplicitParam(name = "outpatientNumber", value = "门诊号")
    })
    @PostMapping("his/hospital/outpatient/fee/detail")
    public GenericResponse getHISOutpatientFee(@RequestBody CommonPojo<OutpatientFeeVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            OutpatientFeeVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<OutpatientFeeDto> list = hisService.getHISOutpatientFee(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }


    /**
     * 30 医疗机构信息查询
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30 医疗机构信息查询", position = 13, notes = "此接口用于获取HIS系统中医疗机构的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "hospitalName", value = "医院名称")
    })
    @PostMapping("his/hospital/detail")
    public GenericResponse getHISHospitalInstitutionDetail(@RequestBody CommonPojo<HospitalInfoVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            HospitalInfoVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<HospitalInfoDto> list = hisService.getHISHospitalInstitutionDetail(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-100 机构信息获取", position = 14, notes = "此接口用于获取HIS系统中 的乡镇卫生院和社区服务中心列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_100),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationName", value = "机构名称")
    })
    @PostMapping("his/organization/list")
    public GenericResponse getHISHospitalInstitutionList(@RequestBody CommonPojo<HospitalOrganizationVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            HospitalOrganizationVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<HospitalOrganizationDto> list = hisService.getHISHospitalInstitutionList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 30-99 获取机构支付方式列表
     */
    @ApiOperation(value = "30-99 获取机构支付方式列表", position = 15, notes = "此接口用于获取HIS系统中机构支付方式列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_99),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构ID/机构编码")
    })
    @PostMapping("his/hospital/payment/list")
    public GenericResponse getHISHospitalPaymentList(@RequestBody CommonPojo<HospitalPaymentVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            HospitalPaymentVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<HospitalPaymentDto> list = hisService.getHISHospitalPaymentList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 30-98 获取挂号费用类型列表
     */
    @ApiOperation(value = "30-98 获取挂号费用类型列表", position = 16, notes = "此接口用于获取HIS系统中挂号费用类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_98),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
    })
    @PostMapping("his/hospital/registration/feeType/list")
    public GenericResponse getHISPatientRegistrationList(@RequestBody CommonPojo<RegistrationFeeTypeVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            RegistrationFeeTypeVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<RegistrationFeeTypeDto> list = hisService.getHISPatientRegistrationList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-1 查询挂号模板", position = 17, notes = "此接口用于获取HIS系统中挂号费用类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_1),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]")
    })
    @PostMapping("his/registration/template/list")
    public GenericResponse getRegistrationTemplateList(@RequestBody CommonPojo<RegistrationTemplateVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            RegistrationTemplateVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<RegistrationTemplateDto> list = hisService.getRegistrationTemplateList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 30-2 查询挂号模板下科室
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30-2 查询挂号模板下科室", position = 18, notes = "此接口用于获取HIS系统中挂号模板下科室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_2),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "templateId", value = "模板ID")
    })
    @PostMapping("his/hospital/registration/section/list")
    public GenericResponse getRegistrationTemplateDepartmentList(@RequestBody CommonPojo<RegistrationTemplateVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            RegistrationTemplateVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<HospitalDepartmentDto> list = hisService.getRegistrationTemplateDepartmentList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 查询科室下--挂号模板列表 -- 自定义方法
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "查询科室下--挂号模板列表", position = 18, notes = "查询科室下挂号模板")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_1),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "departmentId", value = "科室ID"),
            @ApiImplicitParam(name = "departmentName", value = "科室名称[模糊查询]"),
    })
    @PostMapping("his/hospital/section/registration/template/list")
    public GenericResponse getHISRegistrationDepartmentList(@RequestBody CommonPojo<HospitalDepartmentVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            HospitalDepartmentVo hospitalDepartmentVo = commonPojo.getData();
            commonPojo.setInputParameter(hospitalDepartmentVo.getInputParameter());
            //所有科室下挂号模板
            List<RegistrationTemplateDto> registrationTemplateList = hisService.getHISRegistrationTemplateList(commonPojo);
            //所有科室下挂号模板--可以科室查询
            List<HospitalDepartmentDto> list = hisService.getHISDepartmentRegistrationTemplateList(hospitalDepartmentVo, registrationTemplateList);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }


    /**
     * 30-3 查询科室下医生
     */
    @ApiOperation(value = "30-3 查询科室下医生", position = 19, notes = "此接口用于获取HIS科室下医生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_3),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "departmentId", value = "科室编码或科室ID")
    })
    @PostMapping("his/hospital/doctor/list")
    public GenericResponse getHISDepartmentDoctorList(@RequestBody CommonPojo<DoctorVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            DoctorVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<DoctorDto> list = hisService.getHISDepartmentDoctorList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }


    @ApiOperation(value = "30-4 门诊挂号", position = 20, notes = "保存挂号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_4),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "patientName", value = "病人姓名"),
            @ApiImplicitParam(name = "patientSex", value = "病人性别"),
            @ApiImplicitParam(name = "idCardNo", value = "身份证号码"),
            @ApiImplicitParam(name = "userId", value = "收费人员ID"),
            @ApiImplicitParam(name = "fee", value = "金额"),
            @ApiImplicitParam(name = "costTypeId", value = "费用类型ID"),
            @ApiImplicitParam(name = "templateId", value = "模板ID"),
            @ApiImplicitParam(name = "departmentId", value = "科室ID"),
            @ApiImplicitParam(name = "doctorId", value = "医生ID"),
            @ApiImplicitParam(name = "paymentList", value = "缴费方式列表"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商唯一标识"),
            @ApiImplicitParam(name = "createDate", value = "挂号日期[YYYY-MM-DD，注：为空默认为当前时间]"),
            @ApiImplicitParam(name = "paySerialNumber", value = "缴费流水号")
    })
    @PostMapping("his/hospital/registration")
    public GenericResponse getHISRegistration(@RequestBody CommonPojo<RegistrationVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            RegistrationVo data = commonPojo.getData();
            if (StringUtils.isEmpty(data.getCreateDate())){
                data.setCreateDate(DateUtil.getDate("yyyy-MM-dd"));
            }
            List<PayAccountBO> paymentList = data.getPaymentList();
            String paymentListStr = "";
            StringBuilder sbs = new StringBuilder();
            sbs.append("[");
            for (PayAccountBO payAccountBO : paymentList) {
                String orgAccID = payAccountBO.getOrgAccID();
                String paymentId = payAccountBO.getPaymentId();
                String fee = payAccountBO.getFee();
                sbs.append("{").append("\"PaymentID\"").append(":").append("\"").append(paymentId).append("\"").append(",")
                        .append("\"OrgAccID\"").append(":").append("\"").append(orgAccID).append("\"").append(",")
                        .append("\"Fee\"").append(":").append("\"").append(fee).append("\"").append("}")
                        .append(",");
            }
            sbs.append("]");
            paymentListStr = sbs.toString();
            data.setPaymentListStr(paymentListStr);

            commonPojo.setInputParameter(data.getInputParameter());
            List<RegistrationDto> list = hisService.getHISRegistration(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 退挂号
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30-5 退挂号", position = 21, notes = "此接口用于厂商在HIS系统中退挂号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_5),
            @ApiImplicitParam(name = "registrationId", value = "挂号ID"),
            @ApiImplicitParam(name = "userId", value = "收费人员ID"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商唯一标识")
    })
    @PostMapping("his/hospital/withdrawal/registration")
    public GenericResponse getHISWithdrawalRegistration(@RequestBody CommonPojo<RegistrationCancelVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            RegistrationCancelVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            String list = hisService.getHISWithdrawalRegistration(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 30-6 查询挂号记录
     */
    @ApiOperation(value = "30-6 查询挂号记录", position = 22, notes = "此接口用于获取HIS系统中的挂号记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_6),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "idCardNo", value = "身份证号码"),
            @ApiImplicitParam(name = "beginTime", value = "开始日期"),
            @ApiImplicitParam(name = "endTime", value = "结束日期"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]")
    })
    @PostMapping("his/hospital/registration/record")
    public GenericResponse getHISRegistrationRecord(@RequestBody CommonPojo<RegistrationRecordVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            RegistrationRecordVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<RegistrationRecordDto> list = hisService.getHISRegistrationRecord(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 30-7 待缴费门诊费用清单查询
     */
    @ApiOperation(value = "30-7 待缴费门诊费用清单查询", position = 23, notes = "此接口用于获取HIS系统中的费用清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_7),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "registrationId", value = "挂号ID")
    })
    @PostMapping("his/hospital/prepay/outpatient/expenses/bill/list")
    public GenericResponse getHISPrePayOutpatientExpensesBillList(@RequestBody CommonPojo<OutpatientExpensesBillVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            OutpatientExpensesBillVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<OutpatientExpensesBillDto> list = hisService.getHISPrePayOutpatientExpensesBillList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-8 门诊缴费", position = 24, notes = "此接口用于HIS中的门诊收费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_8),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "registrationId", value = "挂号ID"),
            @ApiImplicitParam(name = "recipeIds", value = "处方IDS"),
            @ApiImplicitParam(name = "userId", value = "收费人员ID"),
            @ApiImplicitParam(name = "totalFee", value = "总金额"),
            @ApiImplicitParam(name = "paySerialNumber", value = "缴费流水号"),
            @ApiImplicitParam(name = "organizationCode", value = "机构ID"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商唯一标识"),
            @ApiImplicitParam(name = "paymentList", value = "缴费方式列表")
    })
    @PostMapping("his/hospital/outpatient/payment")
    public GenericResponse getHISOutpatientPayment(@RequestBody CommonPojo<OutpatientPaymentVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            OutpatientPaymentVo data = commonPojo.getData();
            List<PayAccountBO> paymentList = data.getPaymentList();
            String paymentListStr = "";
            StringBuilder sbs = new StringBuilder();
            sbs.append("[");
            for (PayAccountBO payAccountBO : paymentList) {
                String orgAccID = payAccountBO.getOrgAccID();
                String paymentId = payAccountBO.getPaymentId();
                String fee = payAccountBO.getFee();
                sbs.append("{").append("\"PaymentID\"").append(":").append("\"").append(paymentId).append("\"").append(",")
                        .append("\"OrgAccID\"").append(":").append("\"").append(orgAccID).append("\"").append(",")
                        .append("\"Fee\"").append(":").append("\"").append(fee).append("\"").append("}")
                /*.append(",")*/;
            }
            sbs.append("]");
            paymentListStr = sbs.toString();
            data.setPaymentListStr(paymentListStr);

            commonPojo.setInputParameter(data.getInputParameter());
            OutpatientPaymentDto outpatientPaymentDto = hisService.getHISOutpatientPayment(commonPojo);
            return ResponseFormat.retInfo(outpatientPaymentDto);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-81 已缴费列表获取", position = 25, notes = "此接口用于获取HIS系统中门诊缴费记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_81),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "门诊ID")
    })
    @PostMapping("his/hospital/outpatient/paid/list")
    public GenericResponse getHISOutpatientPaidList(@RequestBody CommonPojo<OutpatientPaidVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            OutpatientPaidVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<OutpatientPaidDto> list = hisService.getHISOutpatientPaidList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-82 获取缴费的清单明细", position = 26, notes = "此接口用于获取HIS中的缴费清单明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_82),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "chargeRecordId", value = "收费记录ID")
    })
    @PostMapping("his/hospital/outpatient/bill/detail")
    public GenericResponse getHISOutpatientBillDetail(@RequestBody CommonPojo<ExpenseBillVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            ExpenseBillVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<ExpenseBillDto> list = hisService.getHISOutpatientBillDetail(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-9 门诊退费", position = 27, notes = "此接口用于退APP或微信公众号收取的费用整退")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_9),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "chargeRecordId", value = "收费记录ID"),
            @ApiImplicitParam(name = "vmUserId", value = "虚拟收费人员ID"),
            @ApiImplicitParam(name = "refundAmount", value = "退费金额"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商唯一标识"),
    })
    @PostMapping("his/hospital/outpatient/refund")
    public GenericResponse getHISOutpatientRefund(@RequestBody CommonPojo<OutpatientRefundVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            OutpatientRefundVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            OutpatientRefundDto list = hisService.getHISOutpatientRefund(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-10 获取住院记录", position = 28, notes = "此接口用于获取HIS系统中住院记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_10),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "idCardNo", value = "身份证ID"),
            @ApiImplicitParam(name = "patientName", value = "病人姓名"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]")
    })
    @PostMapping("his/hospital/hospitalization/record/list")
    public GenericResponse getHISHospitalizationRecordList(@RequestBody CommonPojo<InpatientRecordVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            InpatientRecordVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<InpatientRecordDto> list = hisService.getHISInpatientRecordList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-11 住院预交费", position = 29, notes = "此接口用于HIS中的住院预交费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_11),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "userId", value = "收费人员ID"),
            @ApiImplicitParam(name = "hospitalizationId", value = "住院ID"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码/机构ID"),
            @ApiImplicitParam(name = "totalFee", value = "总金额"),
            @ApiImplicitParam(name = "paySerialNumber", value = "缴费流水号"),
            @ApiImplicitParam(name = "paymentList", value = "缴费方式列表"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商唯一标识"),
    })
    @PostMapping("his/hospital/hospitalization/pre/payment")
    public GenericResponse getHISInpatientPrepayment(@RequestBody CommonPojo<PrepaymentVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            PrepaymentVo data = commonPojo.getData();
            List<PayAccountBO> paymentList = data.getPaymentList();
            String paymentListStr = "";
            StringBuilder sbs = new StringBuilder();
            sbs.append("[");
            for (PayAccountBO payAccountBO : paymentList) {
                String orgAccID = payAccountBO.getOrgAccID();
                String paymentId = payAccountBO.getPaymentId();
                String fee = payAccountBO.getFee();
                sbs.append("{").append("\"PaymentID\"").append(":").append("\"").append(paymentId).append("\"").append(",")
                        .append("\"OrgAccID\"").append(":").append("\"").append(orgAccID).append("\"").append(",")
                        .append("\"Fee\"").append(":").append("\"").append(fee).append("\"").append("}")
                /*.append(",")*/;
            }
            sbs.append("]");
            paymentListStr = sbs.toString();
            data.setPaymentListStr(paymentListStr);

            commonPojo.setInputParameter(data.getInputParameter());
            PrepaymentDto list = hisService.getHISInpatientPrepayment(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-12 查询住院预交记录", position = 30, notes = "此接口用于获取HIS系统中预交记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_12),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "hospitalizationId", value = "住院ID"),
    })
    @PostMapping("his/hospital/pre/payment/record/list")
    public GenericResponse getHISInpatientPrepaymentRecordList(@RequestBody CommonPojo<PrepaymentRecordVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            PrepaymentRecordVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<PrepaymentRecordDto> list = hisService.getHISInpatientPrepaymentRecordList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }


    @ApiOperation(value = "30-13 每日清单查询", position = 31, notes = "此接口用于获取HIS系统中每日清单记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_13),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "hospitalizationId", value = "住院ID"),
            @ApiImplicitParam(name = "dailyDay", value = "时间[格式:2019-06-02]"),
    })
    @PostMapping("his/hospital/daily/bill/list")
    public GenericResponse getHISInpatientDailyBillList(@RequestBody CommonPojo<DailyBillVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            DailyBillVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<DailyBillDto> list = hisService.getHISInpatientDailyBillList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }


    @ApiOperation(value = "30-15 获取已完成的检查检验申请列表", position = 32, notes = "此接口用于获取HIS系统中已完成的检查检验申请列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_15),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID,[门诊ID查询门诊对应的检查检验，住院ID查询住院的]"),
    })
    @PostMapping("his/hospital/inspection/apply/form")
    public GenericResponse getHISInspectionReportApplyForm(@RequestBody CommonPojo<InspectionApplyFormVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            InspectionApplyFormVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<InspectionApplyFormDto> list = hisService.getHISInspectionReportApplyForm(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }


    @ApiOperation(value = "30-14 检查检验报告/结果查询", position = 33, notes = "此接口用于获取HIS系统中检查检验报告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_14),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "applyId", value = "申请单ID[30-15接口获取]"),
    })
    @PostMapping("his/hospital/inspection/report/list")
    public GenericResponse getHISInspectionReportList(@RequestBody CommonPojo<InspectionReportVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            InspectionReportVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            InspectionReportDto list = hisService.getHISInspectionReportList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "30-16 获取机构、人员的排班信息", position = 34, notes = "此接口用于获取HIS系统中机构、人员的排班信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_16),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码/机构ID[取接口30返回的ID]"),
            @ApiImplicitParam(name = "doctorId", value = "医生ID"),
            @ApiImplicitParam(name = "beginTime", value = "开始日期"),
            @ApiImplicitParam(name = "endTime", value = "结束日期"),
    })
    @PostMapping("his/hospital/doctor/schedule/list")
    public GenericResponse getHISDoctorScheduleList(@RequestBody CommonPojo<DoctorScheduleVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            DoctorScheduleVo data = commonPojo.getData();
            commonPojo.setInputParameter(data.getInputParameter());
            List<DoctorScheduleDto> list = hisService.getHISDoctorScheduleList(commonPojo);
            return ResponseFormat.retInfo(list);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }
}
