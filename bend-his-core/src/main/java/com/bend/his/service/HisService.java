package com.bend.his.service;

import com.bend.his.bean.entity.*;
import com.bend.his.bean.vo.*;
import com.bend.his.common.CommonPojo;
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
    String getHISAuthConnector(CommonPojo<AuthenticationVo> commonPojo) throws HisException;

    /**
     * No.2 01:登录验证:此接口用于医院用户登录医保报账客户端的安全验证，用户名与密码由HIS系统统一分配
     */
    AuthenticationDto getHISAuth(CommonPojo<AuthenticationVo> commonPojo) throws HisException;


    /**
     * No.3 03:医院综合目录查询(科室、医生、病区、床位):此接口用于获取HIS系统中科室、医师、病区、床位的基本信息
     *
     * @return
     * @throws HisException
     */
    List<ComprehensiveCatalogueDto> getHISComprehensiveCatalogue(CommonPojo<ComprehensiveCatalogueVo> commonPojo) throws HisException;

    /**
     * 根据医生名称查询
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<ComprehensiveCatalogueDto> getHISComprehensiveCatalogueByDoctorInfo(CommonPojo<ComprehensiveCatalogueVo> commonPojo) throws HisException;

    /**
     * No.4 05:医院三大目录查询(药品、诊疗、耗材) 此接口用于获取HIS系统中药品、诊疗、耗材三大目录的基本信息
     */
    List<HospitalThreeCatalogueDto> getHISHospitalThreeCatalogue(CommonPojo<HospitalThreeCatalogueVo> commonPojo) throws HisException;


    /**
     * No.5	06:医院三大目录行数(药品、诊疗、耗材) 此接口用于获取HIS系统中药品、诊疗、耗材三大目录的行数
     */
    List<HospitalThreeCatalogueDto> getHISHospitalThreeCatalogueRows(CommonPojo<HospitalThreeCatalogueVo> commonPojo) throws HisException;

    /**
     * No.6 07:ICD10数据查询:此接口用于获取HIS系统中ICD10的基本信息
     */
    List<ICD10Dto> getHISHospitalICD10(CommonPojo<ICD10Vo> commonPojo) throws HisException;

    /**
     * No.7 08:ICD10数据行数:此接口用于获取HIS系统中ICD10数据的行数
     */
    List<ICD10Dto> getHISHospitalICD10Rows(CommonPojo<ICD10Vo> commonPojo) throws HisException;

    /**
     * No.8 10:住院病人信息查询:此接口用于获取HIS系统中住院病人的基本信息
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<InpatientDto> getHISInpatientList(CommonPojo<InpatientVo> commonPojo) throws HisException;


    /**
     * No.9	12:门诊病人信息查询:此接口用于获取HIS系统中门诊病人的基本信息
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<OutpatientDto> getHISOutpatientList(CommonPojo<OutpatientVo> commonPojo) throws HisException;


    /**
     * No.10 13:中途结算记录查询:此接口用于获取HIS系统中住院病人的结算信息
     * <p>
     * 诊疗结算：住院结算和门诊结算
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<HospitalizationMidwaySettlementDto> getHISHospitalizationSettlement(CommonPojo<HospitalizationMidwaySettlementVo> commonPojo) throws HisException;

    /**
     * No.11 14:住院费用明细查询:此接口用于获取HIS系统中住院费用明细的详细信息
     */
    List<HospitalizationFeeDto> getHISHospitalizationFee(CommonPojo<HospitalizationFeeVo> commonPojo) throws HisException;

    /**
     * No.12 16:门诊费用明细查询:此接口用于获取HIS系统中门诊费用明细的详细信息
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<OutpatientFeeDto> getHISOutpatientFee(CommonPojo<OutpatientFeeVo> commonPojo) throws HisException;


    /**
     * No.13 30：医疗机构信息查询:此接口用于获取HIS系统中医疗机构的详细信息
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<HospitalInfoDto> getHISHospitalInstitutionDetail(CommonPojo<HospitalInfoVo> commonPojo) throws HisException;

    /**
     * No.14 30-100：机构信息获取:此接口用于获取HIS系统中 的乡镇卫生院和社区服务中心列表
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<HospitalOrganizationDto> getHISHospitalInstitutionList(CommonPojo<HospitalOrganizationVo> commonPojo) throws HisException;

    /**
     * No.15 30-99:获取机构支付方式列表:此接口用于获取HIS系统中机构支付方式列表
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<HospitalPaymentDto> getHISHospitalPaymentList(CommonPojo<HospitalPaymentVo> commonPojo) throws HisException;

    /**
     * No.16:30-98:获取挂号费用类型列表
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<RegistrationFeeTypeDto> getHISPatientRegistrationList(CommonPojo<RegistrationFeeTypeVo> commonPojo) throws HisException;


    /**
     * No.17 30-1 查询挂号模板:此接口用于获取HIS系统中挂号模板
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<RegistrationTemplateDto> getRegistrationTemplateList(CommonPojo<RegistrationTemplateVo> commonPojo) throws HisException;

    /**
     * 所有科室下挂号模板--自定义方法
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<RegistrationTemplateDto> getHISRegistrationTemplateList(CommonPojo<HospitalDepartmentVo> commonPojo) throws HisException;

    /**
     * No.18: 30-2:查询挂号模板下科室:此接口用于获取HIS系统中挂号模板下科室
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<HospitalDepartmentDto> getRegistrationTemplateDepartmentList(CommonPojo<RegistrationTemplateVo> commonPojo) throws HisException;


    /**
     * 查所有科室下挂号模板--可以科室查询
     *
     * @param hospitalDepartmentVo        --科室名称
     * @param registrationTemplateDtoList --挂号模板列表
     * @return
     * @throws HisException
     */
    List<HospitalDepartmentDto> getHISDepartmentRegistrationTemplateList(HospitalDepartmentVo hospitalDepartmentVo,
                                                                         List<RegistrationTemplateDto> registrationTemplateDtoList) throws HisException;

    /**
     * No.19 : 30-3:查询科室下医生:此接口用于获取HIS科室下医生
     *
     * @param commonPojo
     */
    List<DoctorDto> getHISDepartmentDoctorList(CommonPojo<DoctorVo> commonPojo) throws HisException;


    /**
     * No.20 : 30-4:门诊挂号:保存挂号信息
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<RegistrationDto> getHISRegistration(CommonPojo<RegistrationVo> commonPojo) throws HisException;

    /**
     * No.21: 30-5 退挂号 : 此接口用于厂商在HIS系统中退挂号
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    String getHISWithdrawalRegistration(CommonPojo<RegistrationCancelVo> commonPojo) throws HisException;

    /**
     * No.22: 30-6 查询挂号记录 : 此接口用于获取HIS系统中的挂号记录
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<RegistrationRecordDto> getHISRegistrationRecord(CommonPojo<RegistrationRecordVo> commonPojo) throws HisException;

    /**
     * No.23 ：30-7：待缴费门诊费用清单查询：此接口用于获取HIS系统中的费用清单
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<OutpatientExpensesBillDto> getHISPrePayOutpatientExpensesBillList(CommonPojo<OutpatientExpensesBillVo> commonPojo) throws HisException;


    /**
     * No.24 ：30-8：门诊缴费：此接口用于HIS中的门诊收费
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    OutpatientPaymentDto getHISOutpatientPayment(CommonPojo<OutpatientPaymentVo> commonPojo) throws HisException;


    /**
     * No.25：30-81:已缴费列表获取:此接口用于获取HIS系统中门诊缴费记录
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<OutpatientPaidDto> getHISOutpatientPaidList(CommonPojo<OutpatientPaidVo> commonPojo) throws HisException;

    /**
     * No.26 30-82：获取缴费清单明细：此接口用于获取HIS中的缴费清单明细
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<ExpenseBillDto> getHISOutpatientBillDetail(CommonPojo<ExpenseBillVo> commonPojo) throws HisException;

    /**
     * No.27:30-9 门诊退费:此接口用于退APP或微信公众号收取的费用整退
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    OutpatientRefundDto getHISOutpatientRefund(CommonPojo<OutpatientRefundVo> commonPojo) throws HisException;

    /**
     * No.28	30-10 获取住院记录：此接口用于获取HIS系统中住院记录
     *
     * @param commonPojo
     */
    List<InpatientRecordDto> getHISInpatientRecordList(CommonPojo<InpatientRecordVo> commonPojo) throws HisException;

    /**
     * No.29	30-11 保存预交金/住院预交费:此接口用于HIS中的住院预交费
     *
     * @param commonPojo
     */
    PrepaymentDto getHISInpatientPrepayment(CommonPojo<PrepaymentVo> commonPojo) throws HisException;

    /**
     * No.30	30-12 查询预交记录:此接口用于获取HIS系统中预交记录
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<PrepaymentRecordDto> getHISInpatientPrepaymentRecordList(CommonPojo<PrepaymentRecordVo> commonPojo) throws HisException;

    /**
     * No.31 30-13 每日清单查询:此接口用于获取HIS系统中每日清单记录
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<DailyBillDto> getHISInpatientDailyBillList(CommonPojo<DailyBillVo> commonPojo) throws HisException;

    /**
     * No.32	30-14 检查检验报告/结果查询:此接口用于获取HIS系统中检查检验报告
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    InspectionReportDto getHISInspectionReportList(CommonPojo<InspectionReportVo> commonPojo) throws HisException;

    /**
     * No.33	30-15 获取已完成的检查检验申请列表:此接口用于获取HIS系统中已完成的检查检验申请列表
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    List<InspectionApplyFormDto> getHISInspectionReportApplyForm(CommonPojo<InspectionApplyFormVo> commonPojo) throws HisException;


    /**
     * No.34	30-16 获取机构、人员的排班信息:此接口用于获取HIS系统中机构、人员的排班信息
     *
     * @param commonPojo
     */
    List<DoctorScheduleDto> getHISDoctorScheduleList(CommonPojo<DoctorScheduleVo> commonPojo) throws HisException;
}
