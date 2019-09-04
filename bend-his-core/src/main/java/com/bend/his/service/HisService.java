package com.bend.his.service;

import com.bend.his.bean.entity.*;
import com.bend.his.common.result.QueryResult;
import com.bend.his.exception.HisException;

import java.util.List;

/**
 * HIS接口--对外提供<br/>
 * 所有接口调用函数HIS_Interface，不同业务传入不同交易编号和交易信息。<br/>
 * C#定义原型：<code>JSonResult OutInfo HIS_Interface(string TradeCode,string InputParameter)</code>
 */
public interface HisService {

    /**
     * No.1 100:接口测试:此接口用于检测接口客户端是否与HIS系统中心服务器相连通
     */
    QueryResult getHISAuthConnector(AuthenticationDto authenticationDto) throws HisException;

    /**
     * No.2 01:登录验证:此接口用于医院用户登录医保报账客户端的安全验证，用户名与密码由HIS系统统一分配
     */
    QueryResult<AuthenticationDto> getHISAuth(AuthenticationDto authenticationDto) throws HisException;

    /**
     * No.3 03:医院综合目录查询(科室、医生、病区、床位):此接口用于获取HIS系统中科室、医师、病区、床位的基本信息
     *
     * @return
     * @throws HisException
     */
    QueryResult<List<ComprehensiveCatalogueDto>> getHISComprehensiveCatalogue(ComprehensiveCatalogueDto comprehensiveCatalogueDto) throws HisException;

    /**
     * No.4 05:医院三大目录查询(药品、诊疗、耗材) 此接口用于获取HIS系统中药品、诊疗、耗材三大目录的基本信息
     *
     * @param hospitalThreeCatalogueDto
     * @return
     * @throws HisException
     */
    QueryResult<List<HospitalThreeCatalogueDto>> getHISHospitalThreeCatalogue(HospitalThreeCatalogueDto hospitalThreeCatalogueDto) throws HisException;


    /**
     * No.5	06:医院三大目录行数(药品、诊疗、耗材) 此接口用于获取HIS系统中药品、诊疗、耗材三大目录的行数
     *
     * @param hospitalThreeCatalogueDto
     * @return
     * @throws HisException
     */
    QueryResult<List<HospitalThreeCatalogueDto>> getHISHospitalThreeCatalogueRows(HospitalThreeCatalogueDto hospitalThreeCatalogueDto) throws HisException;

    /**
     * No.6 07:ICD10数据查询:此接口用于获取HIS系统中ICD10的基本信息
     *
     * @param icd10Dto
     * @return
     * @throws HisException
     */
    QueryResult<List<ICD10Dto>> getHISHospitalICD10(ICD10Dto icd10Dto) throws HisException;

    /**
     * No.7 08:ICD10数据行数:此接口用于获取HIS系统中ICD10数据的行数
     *
     * @param icd10Dto
     * @return
     * @throws HisException
     */
    QueryResult<List<ICD10Dto>> getHISHospitalICD10Rows(ICD10Dto icd10Dto) throws HisException;

    /**
     * No.8 10:住院病人信息查询:此接口用于获取HIS系统中住院病人的基本信息
     *
     * @param inpatientDto
     * @return
     * @throws HisException
     */
    QueryResult<List<InpatientDto>> getHISInpatientList(InpatientDto inpatientDto) throws HisException;


    /**
     * No.9	12:门诊病人信息查询:此接口用于获取HIS系统中门诊病人的基本信息
     *
     * @param outpatientDto
     * @return
     * @throws HisException
     */
    QueryResult<List<OutpatientDto>> getHISOutpatientList(OutpatientDto outpatientDto) throws HisException;


    /**
     * No.10 13:中途结算记录查询:此接口用于获取HIS系统中住院病人的结算信息
     * <p>
     * 诊疗结算：住院结算和门诊结算
     *
     * @param hospitalizationSettlementDto
     * @return
     * @throws HisException
     */
    QueryResult<List<HospitalizationSettlementDto>> getHISHospitalizationSettlement(HospitalizationSettlementDto hospitalizationSettlementDto) throws HisException;

    /**
     * No.11 14:住院费用明细查询:此接口用于获取HIS系统中住院费用明细的详细信息
     */
    QueryResult<List<HospitalizationFeeDto>> getHISHospitalizationFee(HospitalizationFeeDto hospitalizationFeeDto) throws HisException;

    /**
     * No.12 16:门诊费用明细查询:此接口用于获取HIS系统中门诊费用明细的详细信息
     *
     * @param outpatientFeeDto
     * @return
     * @throws HisException
     */
    QueryResult<List<OutpatientFeeDto>> getHISOutpatientFee(OutpatientFeeDto outpatientFeeDto) throws HisException;


    /**
     * No.13 30：医疗机构信息查询:此接口用于获取HIS系统中医疗机构的详细信息
     *
     * @param hospitalOrganizationDto
     * @return
     * @throws HisException
     */
    QueryResult<List<HospitalOrganizationDto>> getHISHospitalInstitutionDetail(HospitalOrganizationDto hospitalOrganizationDto) throws HisException;

    /**
     * No.14 30-100：机构信息获取:此接口用于获取HIS系统中 的乡镇卫生院和社区服务中心列表
     *
     * @param hospitalOrganizationDto
     * @return
     * @throws HisException
     */
    QueryResult<List<HospitalOrganizationDto>> getHISHospitalInstitutionList(HospitalOrganizationDto hospitalOrganizationDto) throws HisException;

    /**
     * No.15 30-99:获取机构支付方式列表:此接口用于获取HIS系统中机构支付方式列表
     *
     * @param hospitalPaymentDto
     * @return
     * @throws HisException
     */
    QueryResult<List<HospitalPaymentDto>> getHISHospitalPaymentList(HospitalPaymentDto hospitalPaymentDto) throws HisException;

    /**
     * No.16:30-98:获取挂号费用类型列表
     *
     * @param registrationFeeTypeDto
     * @return
     * @throws HisException
     */
    QueryResult<List<RegistrationFeeTypeDto>> getHISPatientRegistrationList(RegistrationFeeTypeDto registrationFeeTypeDto) throws HisException;


    /**
     * No.17 30-1 查询挂号模板:此接口用于获取HIS系统中挂号模板
     *
     * @param registrationTemplateDto
     * @return
     * @throws HisException
     */
    QueryResult<List<RegistrationTemplateDto>> getHISHospitalRegistrationTemplateList(RegistrationTemplateDto registrationTemplateDto) throws HisException;

    /**
     * No.18: 30-2:查询挂号模板下科室:此接口用于获取HIS系统中挂号模板下科室
     *
     * @param hospitalDepartmentDto
     * @return
     * @throws HisException
     */
    QueryResult<List<HospitalDepartmentDto>> getHISHospitalRegistrationDepartmentList(HospitalDepartmentDto hospitalDepartmentDto) throws HisException;

    /**
     * No.19 : 30-3:查询科室下医生:此接口用于获取HIS科室下医生
     */
    QueryResult<List<DoctorDto>> getHISDepartmentDoctorList(DoctorDto doctorDto) throws HisException;


    /**
     * No.20 : 30-4:门诊挂号:保存挂号信息
     *
     * @param registrationDto
     * @return
     * @throws HisException
     */
    QueryResult<List<RegistrationDto>> getHISRegistration(RegistrationDto registrationDto) throws HisException;

    /**
     * No.21: 30-5 退挂号 : 此接口用于厂商在HIS系统中退挂号
     *
     * @param registrationDto
     * @return
     * @throws HisException
     */
    QueryResult<String> getHISWithdrawalRegistration(RegistrationDto registrationDto) throws HisException;

    /**
     * No.22: 30-6 查询挂号记录 : 此接口用于获取HIS系统中的挂号记录
     *
     * @param registrationDto
     * @return
     * @throws HisException
     */
    QueryResult<List<RegistrationDto>> getHISRegistrationRecord(RegistrationDto registrationDto) throws HisException;

    /**
     * No.23 ：30-7：待缴费门诊费用清单查询：此接口用于获取HIS系统中的费用清单
     *
     * @param outpatientExpensesBillDto
     * @return
     * @throws HisException
     */
    QueryResult<List<OutpatientExpensesBillDto>> getHISPrePayOutpatientExpensesBillList(OutpatientExpensesBillDto outpatientExpensesBillDto) throws HisException;


    /**
     * No.24 ：30-8：门诊缴费：此接口用于HIS中的门诊收费
     *
     * @param outpatientPaymentDto
     * @return
     * @throws HisException
     */
    QueryResult<OutpatientPaymentDto> getHISOutpatientPayment(OutpatientPaymentDto outpatientPaymentDto) throws HisException;


    /**
     * No.25：30-81:已缴费列表获取:此接口用于获取HIS系统中门诊缴费记录
     *
     * @param outpatientPaymentDto
     * @return
     * @throws HisException
     */
    QueryResult<OutpatientPaymentDto> getHISOutpatientPaidList(OutpatientPaymentDto outpatientPaymentDto) throws HisException;

    /**
     * No.26 30-82：获取缴费清单明细：此接口用于获取HIS中的缴费清单明细
     * @param outpatientPaymentDto
     * @return
     * @throws HisException
     */
    QueryResult<OutpatientPaymentDto> getHISOutpatientBillDetail(OutpatientPaymentDto outpatientPaymentDto) throws HisException;


}
