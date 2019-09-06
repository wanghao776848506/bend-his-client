package com.bend.his.web.controller;

import com.bend.his.bean.entity.*;
import com.bend.his.common.result.QueryResult;
import com.bend.his.constant.TradeCode;
import com.bend.his.exception.HisException;
import com.bend.his.service.HisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
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
    @ApiOperation(value = "01 登录验证", notes = "此接口用于医院用户登录医保报账客户端的安全验证，用户名与密码由HIS系统统一分配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_01),
            @ApiImplicitParam(name = "memberName", value = "用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商编号")
    })
    @PostMapping("his/auth")
    public ResponseEntity<AuthenticationDto> getHISAuth(@RequestBody AuthenticationDto authenticationDto) throws HisException {
        QueryResult<AuthenticationDto> result = hisService.getHISAuth(authenticationDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 03 医院综合目录查询
     *
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "03 医院综合目录查询(科室、医生、病区、床位)", notes = "此接口用于获取HIS系统中科室、医师、病区、床位的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_03),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "directoryType", value = "目录类型[0科室、1医生、2病区、3床位]"),
            @ApiImplicitParam(name = "directoryName", value = "目录名称")
    })
    @PostMapping("his/comprehensive/catalogue")
    public ResponseEntity<List<ComprehensiveCatalogueDto>> getHISComprehensiveCatalogue(@RequestBody ComprehensiveCatalogueDto comprehensiveCatalogueDto) throws HisException {
        QueryResult<List<ComprehensiveCatalogueDto>> result = hisService.getHISComprehensiveCatalogue(comprehensiveCatalogueDto);
        return ResponseEntity.ok(result.getData());
    }


    /**
     * 05 医院三大目录查询
     *
     * @param hospitalThreeCatalogueDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "05 医院三大目录查询(药品、诊疗、耗材)", notes = "此接口用于获取HIS系统中药品、诊疗、耗材三大目录的基本信息")
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
    public ResponseEntity<List<HospitalThreeCatalogueDto>> getHISHospitalThreeCatalogue(@RequestBody HospitalThreeCatalogueDto hospitalThreeCatalogueDto) throws HisException {
        QueryResult<List<HospitalThreeCatalogueDto>> result = hisService.getHISHospitalThreeCatalogue(hospitalThreeCatalogueDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * @param hospitalThreeCatalogueDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "06 医院三大目录行数(药品、诊疗、耗材)", notes = "此接口用于获取HIS系统中药品、诊疗、耗材三大目录的行数")
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
    public ResponseEntity<List<HospitalThreeCatalogueDto>> getHISHospitalThreeCatalogueRows(@RequestBody HospitalThreeCatalogueDto hospitalThreeCatalogueDto) throws HisException {
        QueryResult<List<HospitalThreeCatalogueDto>> result = hisService.getHISHospitalThreeCatalogueRows(hospitalThreeCatalogueDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * ICD10数据查询
     *
     * @param icd10Dto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "07 ICD10数据查询", notes = "此接口用于获取HIS系统中ICD10的基本信息")
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
    public ResponseEntity<List<ICD10Dto>> getHISHospitalICD10(@RequestBody ICD10Dto icd10Dto) throws HisException {
        QueryResult<List<ICD10Dto>> result = hisService.getHISHospitalICD10(icd10Dto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 08 ICD10数据行数
     *
     * @param icd10Dto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "08 ICD10数据行数 ", notes = "此接口用于获取HIS系统中ICD10数据的行数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_08),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "diseaseName", value = "病种名称"),
            @ApiImplicitParam(name = "diseaseCategory", value = "疾病类别"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间")
    })
    @PostMapping("his/hospital/icd10/rows")
    public ResponseEntity<List<ICD10Dto>> getHISHospitalICD10Rows(@RequestBody ICD10Dto icd10Dto) throws HisException {
        QueryResult<List<ICD10Dto>> result = hisService.getHISHospitalICD10Rows(icd10Dto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "10 住院病人信息查询", notes = "此接口用于获取HIS系统中住院病人的基本信息")
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
    public ResponseEntity<List<InpatientDto>> getHISInpatientList(@RequestBody InpatientDto inpatientDto) throws HisException {
        QueryResult<List<InpatientDto>> result = hisService.getHISInpatientList(inpatientDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "12 门诊病人信息查询", notes = "此接口用于获取HIS系统中住院病人的基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_12),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "idCardNo", value = "身份证号码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间"),
            @ApiImplicitParam(name = "endTime", value = "结束时间")
    })
    @PostMapping("his/hospital/outpatient/list")
    public ResponseEntity<List<OutpatientDto>> getHISOutpatientList(@RequestBody OutpatientDto outpatientDto) throws HisException {
        QueryResult<List<OutpatientDto>> result = hisService.getHISOutpatientList(outpatientDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 13 中途结算记录查询
     *
     * @param hospitalizationSettlementDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "13 中途结算记录查询", notes = "此接口用于获取HIS系统中住院病人的结算信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_13),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID/住院ID"),
            @ApiImplicitParam(name = "hospitalizationNo", value = "住院号")
    })
    @PostMapping("his/hospital/halfway/settlement/list")
    public ResponseEntity<List<HospitalizationSettlementDto>> getHISHospitalizationSettlement(@RequestBody HospitalizationSettlementDto hospitalizationSettlementDto) throws HisException {
        QueryResult<List<HospitalizationSettlementDto>> result = hisService.getHISHospitalizationSettlement(hospitalizationSettlementDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 住院费用明细查询
     *
     * @param hospitalizationFeeDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "14 住院费用明细查询", notes = "此接口用于获取HIS系统中住院费用明细的详细信息")
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
    public ResponseEntity<List<HospitalizationFeeDto>> getHISHospitalizationFee(@RequestBody HospitalizationFeeDto hospitalizationFeeDto) throws HisException {
        QueryResult<List<HospitalizationFeeDto>> result = hisService.getHISHospitalizationFee(hospitalizationFeeDto);
        return ResponseEntity.ok(result.getData());
    }


    /**
     * 16 门诊费用明细查询
     */
    @ApiOperation(value = "16 门诊费用明细查询", notes = "此接口用于获取HIS系统中门诊费用明细的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_16),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID"),
            @ApiImplicitParam(name = "outpatientNumber", value = "门诊号")
    })
    @PostMapping("his/hospital/outpatient/fee/detail")
    public ResponseEntity<List<OutpatientFeeDto>> getHISOutpatientFee(@RequestBody OutpatientFeeDto outpatientFeeDto) throws HisException {
        QueryResult<List<OutpatientFeeDto>> result = hisService.getHISOutpatientFee(outpatientFeeDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * @param hospitalOrganizationDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30 医疗机构信息查询", notes = "此接口用于获取HIS系统中医疗机构的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "hospitalName", value = "医院名称")
    })
    @PostMapping("his/hospital/organization/detail")
    public ResponseEntity<List<HospitalOrganizationDto>> getHISHospitalInstitutionDetail(@RequestBody HospitalOrganizationDto hospitalOrganizationDto) throws HisException {
        QueryResult<List<HospitalOrganizationDto>> result = hisService.getHISHospitalInstitutionDetail(hospitalOrganizationDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * @param hospitalOrganizationDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30-100 机构信息获取", notes = "此接口用于获取HIS系统中 的乡镇卫生院和社区服务中心列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_100),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationName", value = "机构名称")
    })
    @PostMapping("his/hospital/organization/list")
    public ResponseEntity<List<HospitalOrganizationDto>> getHISHospitalInstitutionList(@RequestBody HospitalOrganizationDto hospitalOrganizationDto) throws HisException {
        QueryResult<List<HospitalOrganizationDto>> result = hisService.getHISHospitalInstitutionList(hospitalOrganizationDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 30-99 获取机构支付方式列表
     */
    @ApiOperation(value = "30-99 获取机构支付方式列表", notes = "此接口用于获取HIS系统中机构支付方式列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_99),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构ID")
    })
    @PostMapping("his/hospital/payment/list")
    public ResponseEntity<List<HospitalPaymentDto>> getHISHospitalPaymentList(@RequestBody HospitalPaymentDto hospitalPaymentDto) throws HisException {
        QueryResult<List<HospitalPaymentDto>> result = hisService.getHISHospitalPaymentList(hospitalPaymentDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 30-98 获取挂号费用类型列表
     */
    @ApiOperation(value = "30-98 获取挂号费用类型列表", notes = "此接口用于获取HIS系统中挂号费用类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_98),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
    })
    @PostMapping("his/hospital/registration/feeType/list")
    public ResponseEntity<List<RegistrationFeeTypeDto>> getHISPatientRegistrationList(@RequestBody RegistrationFeeTypeDto registrationFeeTypeDto) throws HisException {
        QueryResult<List<RegistrationFeeTypeDto>> result = hisService.getHISPatientRegistrationList(registrationFeeTypeDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "30-1 查询挂号模板", notes = "此接口用于获取HIS系统中挂号费用类型列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_1),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]")
    })
    @PostMapping("his/hospital/registration/template/list")
    public ResponseEntity<List<RegistrationTemplateDto>> getHISHospitalRegistrationTemplateList(@RequestBody RegistrationTemplateDto registrationTemplateDto) throws HisException {
        QueryResult<List<RegistrationTemplateDto>> result = hisService.getHISHospitalRegistrationTemplateList(registrationTemplateDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * @param hospitalDepartmentDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30-2 查询挂号模板下科室", notes = "此接口用于获取HIS系统中挂号模板下科室")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_2),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "templateId", value = "模板ID")
    })
    @PostMapping("his/hospital/registration/section/list")
    public ResponseEntity<List<HospitalDepartmentDto>> getHISHospitalRegistrationDepartmentList(@RequestBody HospitalDepartmentDto hospitalDepartmentDto) throws HisException {
        QueryResult<List<HospitalDepartmentDto>> result = hisService.getHISHospitalRegistrationDepartmentList(hospitalDepartmentDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 查询科室下医生
     *
     * @param doctorDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30-3 查询科室下医生", notes = "此接口用于获取HIS科室下医生")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_3),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]"),
            @ApiImplicitParam(name = "departmentId", value = "科室编码或科室ID")
    })
    @PostMapping("his/hospital/doctor/list")
    public ResponseEntity<List<DoctorDto>> getHISDepartmentDoctorList(@RequestBody DoctorDto doctorDto) throws HisException {
        QueryResult<List<DoctorDto>> result = hisService.getHISDepartmentDoctorList(doctorDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "30-4 门诊挂号", notes = "保存挂号信息")
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
            @ApiImplicitParam(name = "paySerialNumber", value = "缴费流水号")
    })
    @PostMapping("his/hospital/registration")
    public ResponseEntity<List<RegistrationDto>> getHISRegistration(@RequestBody RegistrationDto registrationDto) throws HisException {
        QueryResult<List<RegistrationDto>> result = hisService.getHISRegistration(registrationDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 退挂号
     *
     * @param registrationDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30-5 退挂号", notes = "此接口用于厂商在HIS系统中退挂号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_5),
            @ApiImplicitParam(name = "registrationId", value = "挂号ID"),
            @ApiImplicitParam(name = "userId", value = "收费人员ID"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商唯一标识")
    })
    @PostMapping("his/hospital/withdrawal/registration")
    public ResponseEntity<String> getHISWithdrawalRegistration(@RequestBody RegistrationDto registrationDto) throws HisException {
        QueryResult<String> result = hisService.getHISWithdrawalRegistration(registrationDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 30-6 查询挂号记录
     */
    @ApiOperation(value = "30-6 查询挂号记录", notes = "此接口用于获取HIS系统中的挂号记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_6),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "idCardNo", value = "身份证号码"),
            @ApiImplicitParam(name = "beginTime", value = "开始日期"),
            @ApiImplicitParam(name = "endTime", value = "结束日期"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]")
    })
    @PostMapping("his/hospital/registration/record")
    public ResponseEntity<List<RegistrationDto>> getHISRegistrationRecord(@RequestBody RegistrationDto registrationDto) throws HisException {
        QueryResult<List<RegistrationDto>> result = hisService.getHISRegistrationRecord(registrationDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * 30-7 待缴费门诊费用清单查询
     */
    @ApiOperation(value = "30-7 待缴费门诊费用清单查询", notes = "此接口用于获取HIS系统中的费用清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_7),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "registrationId", value = "挂号ID")
    })
    @PostMapping("his/hospital/prepay/outpatient/expenses/bill/List")
    public ResponseEntity<List<OutpatientExpensesBillDto>> getHISPrePayOutpatientExpensesBillList(@RequestBody OutpatientExpensesBillDto outpatientExpensesBillDto) throws HisException {
        QueryResult<List<OutpatientExpensesBillDto>> result = hisService.getHISPrePayOutpatientExpensesBillList(outpatientExpensesBillDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "30-8 门诊缴费", notes = "此接口用于HIS中的门诊收费")
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
    public ResponseEntity<OutpatientPaymentDto> getHISOutpatientPayment(@RequestBody OutpatientPaymentDto outpatientPaymentDto) throws HisException {
        QueryResult<OutpatientPaymentDto> result = hisService.getHISOutpatientPayment(outpatientPaymentDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "30-81 已缴费列表获取", notes = "此接口用于获取HIS系统中门诊缴费记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_81),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "门诊ID")
    })
    @PostMapping("his/hospital/outpatient/paid/list")
    public ResponseEntity<List<OutpatientPaymentDto>> getHISOutpatientPaidList(@RequestBody OutpatientPaymentDto outpatientPaymentDto) throws HisException {
        QueryResult<List<OutpatientPaymentDto>> result = hisService.getHISOutpatientPaidList(outpatientPaymentDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "30-82 获取缴费的清单明细", notes = "此接口用于获取HIS中的缴费清单明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_82),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "chargeRecordId", value = "收费记录ID")
    })
    @PostMapping("his/hospital/outpatient/bill/detail")
    public ResponseEntity<List<ExpenseBillDto>> getHISOutpatientBillDetail(@RequestBody ExpenseBillDto expenseBillDto) throws HisException {
        QueryResult<List<ExpenseBillDto>> result = hisService.getHISOutpatientBillDetail(expenseBillDto);
        return ResponseEntity.ok(result.getData());
    }

    /**
     * @param outpatientPaymentDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30-9 门诊退费", notes = "此接口用于退APP或微信公众号收取的费用整退")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_9),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "chargeRecordId", value = "收费记录ID"),
            @ApiImplicitParam(name = "vmUserId", value = "虚拟收费人员ID"),
            @ApiImplicitParam(name = "refundAmount", value = "退费金额"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商唯一标识"),
    })
    @PostMapping("his/hospital/outpatient/refund")
    public ResponseEntity<OutpatientPaymentDto> getHISOutpatientRefund(@RequestBody OutpatientPaymentDto outpatientPaymentDto) throws HisException {
        QueryResult<OutpatientPaymentDto> result = hisService.getHISOutpatientRefund(outpatientPaymentDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "30-10 获取住院记录", notes = "此接口用于获取HIS系统中住院记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_10),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "idCardNo", value = "身份证号码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码[取接口30返回的ID]")
    })
    @PostMapping("his/hospital/hospitalization/record/list")
    public ResponseEntity<List<InpatientDto>> getHISHospitalizationRecordList(@RequestBody InpatientDto inpatientDto) throws HisException {
        QueryResult<List<InpatientDto>> result = hisService.getHISInpatientRecordList(inpatientDto);
        return ResponseEntity.ok(result.getData());
    }


    /**
     * @param prepaymentDto
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "30-11 住院预交费", notes = "此接口用于HIS中的住院预交费")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_11),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "userId", value = "收费人员ID"),
            @ApiImplicitParam(name = "hospitalizationId", value = "住院ID"),
            @ApiImplicitParam(name = "totalFee", value = "总金额"),
            @ApiImplicitParam(name = "paySerialNumber", value = "缴费流水号"),
            @ApiImplicitParam(name = "paymentList", value = "缴费方式列表"),
            @ApiImplicitParam(name = "manufacturerNumber", value = "厂商唯一标识"),
    })
    @PostMapping("his/hospital/hospitalization/pre/payment")
    public ResponseEntity<PrepaymentDto> getHISInpatientPrepayment(@RequestBody PrepaymentDto prepaymentDto) throws HisException {
        QueryResult<PrepaymentDto> result = hisService.getHISInpatientPrepayment(prepaymentDto);
        return ResponseEntity.ok(result.getData());
    }


    @ApiOperation(value = "30-12 住院记录查询", notes = "此接口用于获取HIS系统中预交记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_12),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "hospitalizationId", value = "住院ID"),
    })
    @PostMapping("his/hospital/pre/payment/record/list")
    public ResponseEntity<List<PrepaymentDto>> getHISInpatientPrepaymentRecordList(@RequestBody PrepaymentDto prepaymentDto) throws HisException {
        QueryResult<List<PrepaymentDto>> result = hisService.getHISInpatientPrepaymentRecordList(prepaymentDto);
        return ResponseEntity.ok(result.getData());
    }


    @ApiOperation(value = "30-13 每日清单查询", notes = "此接口用于获取HIS系统中每日清单记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_13),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "hospitalizationId", value = "住院ID"),
            @ApiImplicitParam(name = "dailyDay", value = "时间[格式:2019-06-02]"),
    })
    @PostMapping("his/hospital/daily/bill/list")
    public ResponseEntity<List<DailyBillDto>> getHISInpatientDailyBillList(@RequestBody DailyBillDto dailyBillDto) throws HisException {
        QueryResult<List<DailyBillDto>> result = hisService.getHISInpatientDailyBillList(dailyBillDto);
        return ResponseEntity.ok(result.getData());
    }


    @ApiOperation(value = "30-15 获取已完成的检查检验申请列表", notes = "此接口用于获取HIS系统中已完成的检查检验申请列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_15),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID,[门诊ID查询门诊对应的检查检验，住院ID查询住院的]"),
    })
    @PostMapping("his/hospital/inspection/apply/form")
    public ResponseEntity<List<InspectionApplyFormDto>> getHISInspectionReportApplyForm(@RequestBody InspectionApplyFormDto inspectionApplyFormDto) throws HisException {
        QueryResult<List<InspectionApplyFormDto>> result = hisService.getHISInspectionReportApplyForm(inspectionApplyFormDto);
        return ResponseEntity.ok(result.getData());
    }


    @ApiOperation(value = "30-14 检查检验报告/结果查询", notes = "此接口用于获取HIS系统中检查检验报告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_14),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "applyId", value = "申请ID[30-15接口获取]"),
    })
    @PostMapping("his/hospital/inspection/report/list")
    public ResponseEntity<List<InspectionReportDto>> getHISInspectionReportList(@RequestBody InspectionReportDto inspectionReportDto) throws HisException {
        QueryResult<List<InspectionReportDto>> result = hisService.getHISInspectionReportList(inspectionReportDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "30-16 获取机构、人员的排班信息", notes = "此接口用于获取HIS系统中机构、人员的排班信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_30_16),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "organizationCode", value = "机构编码/机构ID[取接口30返回的ID]"),
            @ApiImplicitParam(name = "doctorId", value = "医生ID"),
            @ApiImplicitParam(name = "beginTime", value = "开始日期"),
            @ApiImplicitParam(name = "endTime", value = "结束日期"),
    })
    @PostMapping("his/hospital/doctor/schedule/list")
    public ResponseEntity<List<DoctorScheduleDto>> getHISDoctorScheduleList(@RequestBody DoctorScheduleDto doctorScheduleDto) throws HisException {
        QueryResult<List<DoctorScheduleDto>> result = hisService.getHISDoctorScheduleList(doctorScheduleDto);
        return ResponseEntity.ok(result.getData());
    }


    @ApiOperation(value = "32 住院医嘱查询", notes = "此接口用于获取HIS系统中住院医嘱的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_32),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间[医嘱开具时间：2014-01-01 15:11:22]"),
            @ApiImplicitParam(name = "endTime", value = "结束时间"),
    })
    @PostMapping("his/hospital/doctor/order/list")
    public ResponseEntity<List<MedicalOrderDto>> getHISMedicalOrderList(@RequestBody MedicalOrderDto medicalOrderDto) throws HisException {
        QueryResult<List<MedicalOrderDto>> result = hisService.getHISMedicalOrderList(medicalOrderDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "34 删除费用结算信息", notes = "此接口用于删除门诊、住院的结算相关信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_34),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID【在接口“10 住院病人信息查询”中返回】"),
    })
    @PostMapping("his/settlement/fee/delete")
    public ResponseEntity<String> deleteSettlementFeeByBusinessId(@RequestBody CommonDto commonDto) throws HisException {
        QueryResult<String> result = hisService.deleteSettlementFeeByBusinessId(commonDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "36 保存住院医保信息", notes = "此接口用于 保存 个人住院医保 报账 返回的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_36),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID"),
            @ApiImplicitParam(name = "insuranceNo", value = "医保卡号"),
            @ApiImplicitParam(name = "insuranceTotalFee", value = "医保总费用"),
            @ApiImplicitParam(name = "reimburseFee", value = "报账费用"),
            @ApiImplicitParam(name = "selfPayFee", value = "自付费用"),
            @ApiImplicitParam(name = "otherInfo", value = "其他信息"),
    })
    @PostMapping("his/personal/medical/insurance/save")
    public ResponseEntity<String> savePersonalMedicalInsurance(@RequestBody MedicalInsuranceDto medicalInsuranceDto) throws HisException {
        QueryResult<String> result = hisService.savePersonalMedicalInsurance(medicalInsuranceDto);
        return ResponseEntity.ok(result.getData());
    }

    @ApiOperation(value = "37 删除住院医保信息", notes = "此接口用于删除个人住院医保报账返回的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_37),
            @ApiImplicitParam(name = "authCode", value = "验证码"),
            @ApiImplicitParam(name = "businessId", value = "业务ID【在接口“10住院病人信息查询”中返回】"),
    })
    @PostMapping("his/personal/medical/insurance/delete")
    public ResponseEntity<String> deletePersonalMedicalInsurance(@RequestBody MedicalInsuranceDto medicalInsuranceDto) throws HisException {
        QueryResult<String> result = hisService.deletePersonalMedicalInsurance(medicalInsuranceDto);
        return ResponseEntity.ok(result.getData());
    }




}
