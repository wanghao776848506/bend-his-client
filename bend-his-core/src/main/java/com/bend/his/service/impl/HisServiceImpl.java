package com.bend.his.service.impl;

import com.alibaba.fastjson.JSON;
import com.bend.his.bean.bo.ExpensesBillBO;
import com.bend.his.bean.bo.InspectionReportBO;
import com.bend.his.bean.bo.InspectionReportItemBO;
import com.bend.his.bean.bo.PayAccountBO;
import com.bend.his.bean.entity.*;
import com.bend.his.bean.vo.*;
import com.bend.his.common.CommonPojo;
import com.bend.his.common.HISResult;
import com.bend.his.common.ResponseFormat;
import com.bend.his.config.HISWSClient;
import com.bend.his.constant.DirectoryTypeEnum;
import com.bend.his.constant.IConstant;
import com.bend.his.constant.TradeCode;
import com.bend.his.exception.HisException;
import com.bend.his.service.HisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

@Service("hisService")
public class HisServiceImpl implements HisService {
    private static final Logger logger = LoggerFactory.getLogger(HisServiceImpl.class);
    @Resource
    private HISWSClient hiswsClient;

    @Override
    public String getHISAuthConnector(CommonPojo<AuthenticationVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            return hisResult.getMsg();
        }
    }


    @Override
    public AuthenticationDto getHISAuth(CommonPojo<AuthenticationVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            //与接口文档有出入，此处接口返回[{}]
            List<AuthenticationDto> authenticationDtoList = JSON.parseArray(queryResultMsg, AuthenticationDto.class);
            return authenticationDtoList.get(0);
        }
    }

    @Override
    public List<ComprehensiveCatalogueDto> getHISComprehensiveCatalogue(CommonPojo<ComprehensiveCatalogueVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, ComprehensiveCatalogueDto.class);
        }
    }

    /**
     * 1、返回综合目录数据
     * 2、筛选出指定医生数据
     * 3、封装指定医生其他信息
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    @Override
    public List<ComprehensiveCatalogueDto> getHISComprehensiveCatalogueByDoctorInfo(CommonPojo<ComprehensiveCatalogueVo> commonPojo) throws HisException {
        ComprehensiveCatalogueVo comprehensiveCatalogueVo = commonPojo.getData();
        String doctorName = comprehensiveCatalogueVo.getDirectoryName();//目录名称:医生名称
        String directoryType = comprehensiveCatalogueVo.getDirectoryType();//目录类型:1
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            List<ComprehensiveCatalogueDto> catalogueDtoList = JSON.parseArray(queryResultMsg, ComprehensiveCatalogueDto.class);
            List<ComprehensiveCatalogueDto> doctorList = catalogueDtoList;
            //筛选出指定医生数据
            if (!StringUtils.isEmpty(doctorName)) {
                doctorList = catalogueDtoList.stream().filter(s -> doctorName.equals(s.getDirectoryName())).collect(Collectors.toList());
            }
            //去重字符串list.stream().distinct()
            //对象去重
            doctorList = doctorList.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(ComprehensiveCatalogueDto::getDirectoryCode))), ArrayList::new));

            //查询医生数据,处理备注[目录类型1： 返回医生所在科室的编码;目录类型3： 返回床位所在的病区编码.]
            if (DirectoryTypeEnum.DOCTOR.getValue().equals(directoryType)) {
                if (!CollectionUtils.isEmpty(doctorList)) {
                    for (ComprehensiveCatalogueDto dto : doctorList) {
                        String name = dto.getDirectoryName(); //医生姓名
                        String doctorId = dto.getDirectoryCode();//医生ID
                        String departmentId = dto.getRemark();//备注--科室ID

                        DoctorDto doctorDto = new DoctorDto();
                        doctorDto.setDoctorId(doctorId);
                        doctorDto.setDoctorName(name);
                        doctorDto.setDepartmentId(departmentId);
                        //String departmentName = this.getSectionName(dto, departmentId); //科室名称
                        //doctorDto.setDepartmentName(departmentName);
                        dto.setDoctorDto(doctorDto);
                    }
                }
            }
            return doctorList;
        }
    }

    @Override
    public List<HospitalThreeCatalogueDto> getHISHospitalThreeCatalogue(CommonPojo<HospitalThreeCatalogueVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, HospitalThreeCatalogueDto.class);
        }
    }

    @Override
    public List<HospitalThreeCatalogueDto> getHISHospitalThreeCatalogueRows(CommonPojo<HospitalThreeCatalogueVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, HospitalThreeCatalogueDto.class);
        }
    }

    @Override
    public List<ICD10Dto> getHISHospitalICD10(CommonPojo<ICD10Vo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, ICD10Dto.class);
        }
    }

    @Override
    public List<ICD10Dto> getHISHospitalICD10Rows(CommonPojo<ICD10Vo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, ICD10Dto.class);
        }
    }


    @Override
    public List<InpatientDto> getHISInpatientList(CommonPojo<InpatientVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, InpatientDto.class);
        }
    }

    @Override
    public List<OutpatientDto> getHISOutpatientList(CommonPojo<OutpatientVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, OutpatientDto.class);
        }
    }

    @Override
    public List<HospitalizationMidwaySettlementDto> getHISHospitalizationSettlement(CommonPojo<HospitalizationMidwaySettlementVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, HospitalizationMidwaySettlementDto.class);
        }
    }

    @Override
    public List<HospitalizationFeeDto> getHISHospitalizationFee(CommonPojo<HospitalizationFeeVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, HospitalizationFeeDto.class);
        }
    }

    @Override
    public List<OutpatientFeeDto> getHISOutpatientFee(CommonPojo<OutpatientFeeVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, OutpatientFeeDto.class);
        }
    }


    @Override
    public List<HospitalInfoDto> getHISHospitalInstitutionDetail(CommonPojo<HospitalInfoVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, HospitalInfoDto.class);
        }
    }

    @Override
    public List<HospitalOrganizationDto> getHISHospitalInstitutionList(CommonPojo<HospitalOrganizationVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, HospitalOrganizationDto.class);
        }
    }

    @Override
    public List<HospitalPaymentDto> getHISHospitalPaymentList(CommonPojo<HospitalPaymentVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, HospitalPaymentDto.class);
        }
    }

    @Override
    public List<RegistrationFeeTypeDto> getHISPatientRegistrationList(CommonPojo<RegistrationFeeTypeVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();

            return JSON.parseArray(queryResultMsg, RegistrationFeeTypeDto.class);
        }
    }

    @Override
    public List<RegistrationTemplateDto> getRegistrationTemplateList(CommonPojo<RegistrationTemplateVo> commonPojo) throws HisException {
        RegistrationTemplateVo registrationTemplateVo = commonPojo.getData();
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            List<RegistrationTemplateDto> registrationTemplateDtoList = JSON.parseArray(queryResultMsg, RegistrationTemplateDto.class);
            for (RegistrationTemplateDto registrationTemplateDto : registrationTemplateDtoList) {
                //设置机构编码
                registrationTemplateDto.setOrganizationCode(registrationTemplateVo.getOrganizationCode());
            }
            return registrationTemplateDtoList;
        }
    }

    @Override
    public List<RegistrationTemplateDto> getHISRegistrationTemplateList(CommonPojo<HospitalDepartmentVo> commonPojo) throws HisException {
        HospitalDepartmentVo hospitalDepartmentVo = commonPojo.getData();
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            List<RegistrationTemplateDto> registrationTemplateDtoList = JSON.parseArray(queryResultMsg, RegistrationTemplateDto.class);
            for (RegistrationTemplateDto registrationTemplateDto : registrationTemplateDtoList) {
                //设置机构编码
                registrationTemplateDto.setOrganizationCode(hospitalDepartmentVo.getOrganizationCode());
            }
            return registrationTemplateDtoList;
        }
    }

    @Override
    public List<HospitalDepartmentDto> getRegistrationTemplateDepartmentList(CommonPojo<RegistrationTemplateVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, HospitalDepartmentDto.class);
        }
    }

    @Override
    public List<HospitalDepartmentDto> getHISDepartmentRegistrationTemplateList(HospitalDepartmentVo hospitalDepartmentVo,
                                                                                List<RegistrationTemplateDto> registrationTemplateDtoList) throws HisException {
        //所有科室
        List<HospitalDepartmentDto> departmentAllList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(registrationTemplateDtoList)) {
            for (RegistrationTemplateDto registrationTemplate : registrationTemplateDtoList) {
                //
                RegistrationTemplateVo vo = new RegistrationTemplateVo();
                vo.setAuthCode(hospitalDepartmentVo.getAuthCode());
                vo.setTemplateId(registrationTemplate.getTemplateId());
                vo.setOrganizationCode(registrationTemplate.getOrganizationCode());

                //查询挂号模板下的科室列表
                List<HospitalDepartmentDto> departmentList = this.getHISRegistrationDepartmentList(vo,registrationTemplate);
                if (!CollectionUtils.isEmpty(departmentList)) {
                    departmentAllList.addAll(departmentList);
                }
            }
        }
        //科室名称筛选
        String departmentName = hospitalDepartmentVo.getDepartmentName();
        if (!StringUtils.isEmpty(departmentName)) {
            List<HospitalDepartmentDto> list = departmentAllList.stream().filter(s -> s.getDepartmentName().contains(departmentName)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(list)) {
                departmentAllList = list;
            }
        }
        //科室ID精确查找
        String departmentId = hospitalDepartmentVo.getDepartmentId();
        if (!StringUtils.isEmpty(departmentId)) {
            List<HospitalDepartmentDto> list = departmentAllList.stream().filter(s -> departmentId.equals(s.getDepartmentId())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(list)) {
                departmentAllList = list;
            }
        }

        //相同元素分组
        return mergeMapValues(departmentAllList);
    }

    //元素分组
    private List<HospitalDepartmentDto> mergeMapValues(List<HospitalDepartmentDto> departmentAllList) {
        Map<String, List<HospitalDepartmentDto>> listMap = departmentAllList.stream().collect(Collectors.groupingBy(HospitalDepartmentDto::getDepartmentId));
        //System.out.println(JSON.toJSONString(listMap));
        //把map的value重组,放入新的list中
        List<HospitalDepartmentDto> newList = new ArrayList<>();
        listMap.forEach((k, v) -> {
            HospitalDepartmentDto hospitalDepartmentDto = v.get(0);
            hospitalDepartmentDto.setRegistrationTemplateDto(null);
            List<RegistrationTemplateDto> tmpList = new ArrayList<>();
            for (HospitalDepartmentDto departmentDto : v) {
                tmpList.add(departmentDto.getRegistrationTemplateDto());
            }
            hospitalDepartmentDto.setRegistrationTemplateList(tmpList);
            newList.add(hospitalDepartmentDto);

        });
        //newList.stream().forEach(System.out::println);

        return newList;
    }


    private List<HospitalDepartmentDto> getHISRegistrationDepartmentList(RegistrationTemplateVo vo,RegistrationTemplateDto registrationTemplate) throws HisException {
        //自定义参数
        CommonPojo<RegistrationTemplateVo> commonPojo = CommonPojo.<RegistrationTemplateVo>newBuilder().build();
        commonPojo.setTradeCode(TradeCode.TRADE_30_2);//查询挂号模板下科室
        commonPojo.setInputParameter(vo.getInputParameter());

        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            List<HospitalDepartmentDto> hospitalDepartmentDtoList = JSON.parseArray(queryResultMsg, HospitalDepartmentDto.class);
            if (!CollectionUtils.isEmpty(hospitalDepartmentDtoList)){
                for (HospitalDepartmentDto hospitalDepartmentDto : hospitalDepartmentDtoList) {
                    //设置挂号模板(当前的)
                    hospitalDepartmentDto.setRegistrationTemplateDto(registrationTemplate);
                }
            }
            return hospitalDepartmentDtoList;
        }
    }

    @Override
    public List<DoctorDto> getHISDepartmentDoctorList(CommonPojo<DoctorVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, DoctorDto.class);
        }
    }

    @Override
    public List<RegistrationDto> getHISRegistration(CommonPojo<RegistrationVo> commonPojo) throws HisException {
        RegistrationVo registrationVo = commonPojo.getData();
        List<PayAccountBO> paymentList = registrationVo.getPaymentList();
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
        registrationVo.setPaymentListStr(paymentListStr);

        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, RegistrationDto.class);
        }
    }

    @Override
    public String getHISWithdrawalRegistration(CommonPojo<RegistrationCancelVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            return hisResult.getMsg();
        }
    }

    @Override
    public List<RegistrationRecordDto> getHISRegistrationRecord(CommonPojo<RegistrationRecordVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, RegistrationRecordDto.class);
        }
    }

    @Override
    public List<OutpatientExpensesBillDto> getHISPrePayOutpatientExpensesBillList(CommonPojo<OutpatientExpensesBillVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            List<OutpatientExpensesBillDto> outpatientExpensesBillList = JSON.parseArray(queryResultMsg, OutpatientExpensesBillDto.class);
            if (!CollectionUtils.isEmpty(outpatientExpensesBillList)) {
                for (OutpatientExpensesBillDto dto : outpatientExpensesBillList) {
                    String prescriptionDetail = dto.getPrescriptionDetail();
                    List<ExpensesBillBO> expensesBillBOList = JSON.parseArray(prescriptionDetail, ExpensesBillBO.class);
                    dto.setExpensesBillList(expensesBillBOList);
                }
            }
            return JSON.parseArray(queryResultMsg, OutpatientExpensesBillDto.class);
        }
    }

    @Override
    public OutpatientPaymentDto getHISOutpatientPayment(CommonPojo<OutpatientPaymentVo> commonPojo) throws HisException {
        OutpatientPaymentVo outpatientPaymentVo = commonPojo.getData();
        List<PayAccountBO> paymentList = outpatientPaymentVo.getPaymentList();
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
        outpatientPaymentVo.setPaymentListStr(paymentListStr);

        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseObject(queryResultMsg, OutpatientPaymentDto.class);
        }
    }

    @Override
    public List<OutpatientPaidDto> getHISOutpatientPaidList(CommonPojo<OutpatientPaidVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, OutpatientPaidDto.class);
        }
    }

    @Override
    public List<ExpenseBillDto> getHISOutpatientBillDetail(CommonPojo<ExpenseBillVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, ExpenseBillDto.class);
        }
    }

    @Override
    public OutpatientRefundDto getHISOutpatientRefund(CommonPojo<OutpatientRefundVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseObject(queryResultMsg, OutpatientRefundDto.class);
        }
    }

    @Override
    public List<InpatientRecordDto> getHISInpatientRecordList(CommonPojo<InpatientRecordVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, InpatientRecordDto.class);
        }
    }

    @Override
    public PrepaymentDto getHISInpatientPrepayment(CommonPojo<PrepaymentVo> commonPojo) throws HisException {
        PrepaymentVo prepaymentVo = commonPojo.getData();
        List<PayAccountBO> paymentList = prepaymentVo.getPaymentList();
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
        prepaymentVo.setPaymentListStr(paymentListStr);

        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseObject(queryResultMsg, PrepaymentDto.class);
        }
    }

    @Override
    public List<PrepaymentRecordDto> getHISInpatientPrepaymentRecordList(CommonPojo<PrepaymentRecordVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, PrepaymentRecordDto.class);
        }
    }

    @Override
    public List<DailyBillDto> getHISInpatientDailyBillList(CommonPojo<DailyBillVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, DailyBillDto.class);
        }
    }

    @Override
    public InspectionReportDto getHISInspectionReportList(CommonPojo<InspectionReportVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            InspectionReportDto inspectionReport = JSON.parseObject(queryResultMsg, InspectionReportDto.class);
            if (null != inspectionReport) {
                String perInfo = inspectionReport.getPerInfo();//患者信息、标本号 结论 结果等
                InspectionReportBO inspectionReportBO = JSON.parseObject(perInfo, InspectionReportBO.class);
                inspectionReport.setInspectionReportBO(inspectionReportBO);
                //指标值  参考值等
                String reportIdx = inspectionReport.getIdx();
                InspectionReportItemBO inspectionReportItemBO = JSON.parseObject(reportIdx, InspectionReportItemBO.class);
                if (null != inspectionReportItemBO) {
                    String idx = inspectionReportItemBO.getIdx();
                    List<InspectionReportItemBO> inspectionReportItemList = JSON.parseArray(idx, InspectionReportItemBO.class);
                    inspectionReportBO.setInspectionReportItemList(inspectionReportItemList);
                }
            }
            return inspectionReport;
        }
    }

    @Override
    public List<InspectionApplyFormDto> getHISInspectionReportApplyForm(CommonPojo<InspectionApplyFormVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, InspectionApplyFormDto.class);
        }
    }

    @Override
    public List<DoctorScheduleDto> getHISDoctorScheduleList(CommonPojo<DoctorScheduleVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, DoctorScheduleDto.class);
        }
    }
}
