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
import com.bend.his.common.request.QueryRequest;
import com.bend.his.common.result.QueryResult;
import com.bend.his.config.HISWSClient;
import com.bend.his.constant.DirectoryTypeEnum;
import com.bend.his.constant.IConstant;
import com.bend.his.exception.HisException;
import com.bend.his.service.HisService;
import com.bend.his.wsdl.HISInterfaceResponse;
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            return hisResult.getMsg();
        }
    }


    @Override
    public AuthenticationDto getHISAuth(CommonPojo<AuthenticationVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
                //查询挂号模板下的科室列表
                List<HospitalDepartmentDto> departmentList = this.getHISRegistrationDepartmentList(registrationTemplate);
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


    private List<HospitalDepartmentDto> getHISRegistrationDepartmentList(RegistrationTemplateDto registrationTemplate) throws HisException {
        //自定义参数
        CommonPojo<RegistrationTemplateDto> commonPojo = CommonPojo.<RegistrationTemplateDto>newBuilder().build();
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            List<HospitalDepartmentDto> hospitalDepartmentDtoList = JSON.parseArray(queryResultMsg, HospitalDepartmentDto.class);
            for (HospitalDepartmentDto hospitalDepartmentDto : hospitalDepartmentDtoList) {
                //设置挂号模板(当前的)
                hospitalDepartmentDto.setRegistrationTemplateDto(registrationTemplate);
            }
            return hospitalDepartmentDtoList;
        }
    }

    @Override
    public List<DoctorDto> getHISDepartmentDoctorList(CommonPojo<DoctorVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
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
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            return hisResult.getMsg();
        }
    }

    @Override
    public QueryResult<List<RegistrationDto>> getHISRegistrationRecord(RegistrationDto registrationDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(registrationDto.getTradeCode());
        queryRequest.setInputParameter(registrationDto.createJSONObject());

        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<RegistrationDto> registrationDtoList = JSON.parseArray(queryResultMsg, RegistrationDto.class);
            queryResult.setData(registrationDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<OutpatientExpensesBillDto>> getHISPrePayOutpatientExpensesBillList(OutpatientExpensesBillDto outpatientExpensesBillDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientExpensesBillDto.getTradeCode());
        queryRequest.setInputParameter(outpatientExpensesBillDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<OutpatientExpensesBillDto> outpatientExpensesBillDtoList = JSON.parseArray(queryResultMsg, OutpatientExpensesBillDto.class);
            if (!CollectionUtils.isEmpty(outpatientExpensesBillDtoList)) {
                for (OutpatientExpensesBillDto dto : outpatientExpensesBillDtoList) {
                    String prescriptionDetail = dto.getPrescriptionDetail();
                    List<ExpensesBillBO> expensesBillBOList = JSON.parseArray(prescriptionDetail, ExpensesBillBO.class);
                    dto.setExpensesBillList(expensesBillBOList);
                }
            }
            queryResult.setData(outpatientExpensesBillDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<OutpatientPaymentDto> getHISOutpatientPayment(OutpatientPaymentDto outpatientPaymentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientPaymentDto.getTradeCode());
        List<PayAccountBO> paymentList = outpatientPaymentDto.getPaymentList();
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
        outpatientPaymentDto.setPaymentListStr(paymentListStr);

        queryRequest.setInputParameter(outpatientPaymentDto.createJSONObject());

        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            OutpatientPaymentDto outpatientPayment = JSON.parseObject(queryResultMsg, OutpatientPaymentDto.class);
            queryResult.setData(outpatientPayment);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<OutpatientPaymentDto>> getHISOutpatientPaidList(OutpatientPaymentDto outpatientPaymentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientPaymentDto.getTradeCode());
        queryRequest.setInputParameter(outpatientPaymentDto.createJSONObject());

        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<OutpatientPaymentDto> outpatientPaymentDtoList = JSON.parseArray(queryResultMsg, OutpatientPaymentDto.class);
            queryResult.setData(outpatientPaymentDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<ExpenseBillDto>> getHISOutpatientBillDetail(ExpenseBillDto expenseBillDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(expenseBillDto.getTradeCode());
        queryRequest.setInputParameter(expenseBillDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<ExpenseBillDto> expenseBillDtoList = JSON.parseArray(queryResultMsg, ExpenseBillDto.class);
            queryResult.setData(expenseBillDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<OutpatientPaymentDto> getHISOutpatientRefund(OutpatientPaymentDto outpatientPaymentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientPaymentDto.getTradeCode());
        queryRequest.setInputParameter(outpatientPaymentDto.createJSONObject());

        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            OutpatientPaymentDto outpatientPayment = JSON.parseObject(queryResultMsg, OutpatientPaymentDto.class);
            queryResult.setData(outpatientPayment);
        }
        return queryResult;
    }

    @Override
    public List<InpatientRecordDto> getHISInpatientRecordList(CommonPojo<InpatientRecordVo> commonPojo) throws HisException {
        HISResult hisResult = hiswsClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, InpatientRecordDto.class);
        }
    }

    @Override
    public QueryResult<PrepaymentDto> getHISInpatientPrepayment(PrepaymentDto prepaymentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(prepaymentDto.getTradeCode());

        List<PayAccountBO> paymentList = prepaymentDto.getPaymentList();
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
        prepaymentDto.setPaymentListStr(paymentListStr);

        queryRequest.setInputParameter(prepaymentDto.createJSONObject());

        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            PrepaymentDto prepayment = JSON.parseObject(queryResultMsg, PrepaymentDto.class);
            queryResult.setData(prepayment);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<PrepaymentDto>> getHISInpatientPrepaymentRecordList(PrepaymentDto prepaymentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(prepaymentDto.getTradeCode());
        queryRequest.setInputParameter(prepaymentDto.createJSONObject());

        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<PrepaymentDto> prepaymentDtoList = JSON.parseArray(queryResultMsg, PrepaymentDto.class);
            queryResult.setData(prepaymentDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<DailyBillDto>> getHISInpatientDailyBillList(DailyBillDto dailyBillDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(dailyBillDto.getTradeCode());
        queryRequest.setInputParameter(dailyBillDto.createJSONObject());

        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<DailyBillDto> dailyBillDtoList = JSON.parseArray(queryResultMsg, DailyBillDto.class);
            queryResult.setData(dailyBillDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<InspectionReportDto> getHISInspectionReportList(InspectionReportDto inspectionReportDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(inspectionReportDto.getTradeCode());
        queryRequest.setInputParameter(inspectionReportDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            InspectionReportDto inspectionReport = JSON.parseObject(queryResultMsg, InspectionReportDto.class);
            if (null != inspectionReport) {
                String perInfo = inspectionReport.getPerInfo();
                InspectionReportBO inspectionReportBO = JSON.parseObject(perInfo, InspectionReportBO.class);
                inspectionReport.setInspectionReportBO(inspectionReportBO);
                //
                String reportIdx = inspectionReport.getIdx();
                InspectionReportItemBO inspectionReportItemBO = JSON.parseObject(reportIdx, InspectionReportItemBO.class);
                if (null != inspectionReportItemBO) {
                    String idx = inspectionReportItemBO.getIdx();
                    List<InspectionReportItemBO> inspectionReportItemList = JSON.parseArray(idx, InspectionReportItemBO.class);
                    inspectionReportBO.setInspectionReportItemList(inspectionReportItemList);
                }
            }
            queryResult.setData(inspectionReport);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<InspectionApplyFormDto>> getHISInspectionReportApplyForm(InspectionApplyFormDto inspectionApplyFormDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(inspectionApplyFormDto.getTradeCode());
        queryRequest.setInputParameter(inspectionApplyFormDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<InspectionApplyFormDto> inspectionApplyFormDtoList = JSON.parseArray(queryResultMsg, InspectionApplyFormDto.class);
            queryResult.setData(inspectionApplyFormDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<DoctorScheduleDto>> getHISDoctorScheduleList(DoctorScheduleDto doctorScheduleDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(doctorScheduleDto.getTradeCode());
        queryRequest.setInputParameter(doctorScheduleDto.createJSONObject());

        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<DoctorScheduleDto> doctorScheduleDtoList = JSON.parseArray(queryResultMsg, DoctorScheduleDto.class);
            queryResult.setData(doctorScheduleDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<MedicalOrderDto>> getHISMedicalOrderList(MedicalOrderDto medicalOrderDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(medicalOrderDto.getTradeCode());
        queryRequest.setInputParameter(medicalOrderDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<MedicalOrderDto> medicalOrderDtoList = JSON.parseArray(queryResultMsg, MedicalOrderDto.class);
            queryResult.setData(medicalOrderDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<String> deleteSettlementFeeByBusinessId(CommonDto commonDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(commonDto.getTradeCode());
        queryRequest.setInputParameter(commonDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            queryResult.setMsg("删除成功!");//返回的msg文本比较长
        }
        return queryResult;
    }

    @Override
    public QueryResult<String> savePersonalMedicalInsurance(MedicalInsuranceDto medicalInsuranceDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(medicalInsuranceDto.getTradeCode());
        queryRequest.setInputParameter(medicalInsuranceDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            queryResult.setMsg("保存成功!");//返回的msg文本比较长
        }
        return queryResult;
    }

    @Override
    public QueryResult<String> deletePersonalMedicalInsurance(MedicalInsuranceDto medicalInsuranceDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(medicalInsuranceDto.getTradeCode());
        queryRequest.setInputParameter(medicalInsuranceDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            queryResult.setMsg("删除成功!");//返回的msg文本比较长
        }
        return queryResult;
    }

    @Override
    public QueryResult<String> saveExpenseSettlementToHis(ExpenseSettlementDto expenseSettlementDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(expenseSettlementDto.getTradeCode());
        queryRequest.setInputParameter(expenseSettlementDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            queryResult.setMsg("回写至基层系统成功!");//返回的msg文本比较长
        }
        return queryResult;
    }

    @Override
    public QueryResult<String> saveThreeCataloguePairCodeToHis(ThreeCataloguePairCodeDto threeCataloguePairCodeDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(threeCataloguePairCodeDto.getTradeCode());
        queryRequest.setInputParameter(threeCataloguePairCodeDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            queryResult.setMsg("信息回写成功!");//返回的msg文本比较长
        }
        return queryResult;
    }

    @Override
    public QueryResult<String> saveMedicalInsuranceToHis(MedicalInsuranceDto medicalInsuranceDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(medicalInsuranceDto.getTradeCode());
        queryRequest.setInputParameter(medicalInsuranceDto.createJSONObject());
        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            queryResult.setMsg("信息回写成功!");//返回的msg文本比较长
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<PacsItemDto>> getPacsItemList(PacsItemDto pacsItemDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(pacsItemDto.getTradeCode());
        queryRequest.setInputParameter(pacsItemDto.createJSONObject());

        logger.debug("接口交易参数:{}", queryRequest.getInputParameter());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        logger.debug("接口响应数据:{}", hisInterfaceResult);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<PacsItemDto> pacsItemDtoList = JSON.parseArray(queryResultMsg, PacsItemDto.class);
            queryResult.setData(pacsItemDtoList);
        }
        return queryResult;
    }
}
