package com.bend.his.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bend.his.bean.bo.PayAccountBO;
import com.bend.his.bean.entity.*;
import com.bend.his.common.request.QueryRequest;
import com.bend.his.common.result.QueryResult;
import com.bend.his.config.HISPublicWSClient;
import com.bend.his.config.HISWSClient;
import com.bend.his.constant.IConstant;
import com.bend.his.exception.HisException;
import com.bend.his.service.HisService;
import com.bend.his.wsdl.HISInterfaceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.wsdl.WSDLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("hisService")
@Slf4j
public class HisServiceImpl implements HisService {

    @Resource
    private HISWSClient hiswsClient;

    /**
     * for 公卫
     */
    @Resource
    private HISPublicWSClient hisPublicWSClient;


    @Override
    public QueryResult getHISAuthConnector(AuthenticationDto authenticationDto) throws HisException{
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
//        String tradeCode = "100";
//        inputJson.put("厂商编号", "510303001");
        queryRequest.setTradeCode(queryRequest.getTradeCode());
        queryRequest.setInputParameter(authenticationDto.createJSONObject());
        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        return JSON.parseObject(hisInterfaceResponse.getHISInterfaceResult(), QueryResult.class);
    }

    @Override
    public QueryResult<AuthenticationDto> getHISAuth(AuthenticationDto authenticationDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(authenticationDto.getTradeCode());
        queryRequest.setInputParameter(authenticationDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /**/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray array =  JSON.parseArray(queryResultMsg);
            AuthenticationDto dto = JSON.parseObject(array.getString(0), AuthenticationDto.class);
            queryResult.setData(dto);
            return queryResult;
        } else {
            throw new HisException("登陆失败!");
        }

    }

    @Override
    public QueryResult<PublicAuthDto> getHISPublicAuth(PublicAuthDto publicAuthDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(publicAuthDto.getTradeCode());
        queryRequest.setInputParameter(publicAuthDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /**/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            PublicAuthDto dto = JSON.parseObject(queryResultMsg, PublicAuthDto.class);
            queryResult.setData(dto);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<ComprehensiveCatalogueDto>> getHISComprehensiveCatalogue(ComprehensiveCatalogueDto comprehensiveCatalogueDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(comprehensiveCatalogueDto.getTradeCode());
        queryRequest.setInputParameter(comprehensiveCatalogueDto.createJSONObject());
        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();

            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<ComprehensiveCatalogueDto> comprehensiveCatalogueDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                ComprehensiveCatalogueDto catalogueDto = JSON.parseObject(jsonArrayString, ComprehensiveCatalogueDto.class);
                comprehensiveCatalogueDtoList.add(catalogueDto);
            }
            queryResult.setData(comprehensiveCatalogueDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<HospitalThreeCatalogueDto>> getHISHospitalThreeCatalogue(HospitalThreeCatalogueDto hospitalThreeCatalogueDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalThreeCatalogueDto.getTradeCode());
        queryRequest.setInputParameter(hospitalThreeCatalogueDto.createJSONObject());
        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            //TODO 接口请求成功或失败逻辑处理
        }
        String queryResultMsg = queryResult.getMsg();
        JSONArray jsonArray = JSON.parseArray(queryResultMsg);

        List<HospitalThreeCatalogueDto> hospitalThreeCatalogueDtoList = new ArrayList<>();

        for (int i = 0; i < jsonArray.size(); i++) {
            String jsonArrayString = jsonArray.getString(i);
            HospitalThreeCatalogueDto threeCatalogueDto = JSON.parseObject(jsonArrayString, HospitalThreeCatalogueDto.class);
            hospitalThreeCatalogueDtoList.add(threeCatalogueDto);
        }
        queryResult.setData(hospitalThreeCatalogueDtoList);
        return queryResult;
    }


    @Override
    public QueryResult<List<HospitalThreeCatalogueDto>> getHISHospitalThreeCatalogueRows(HospitalThreeCatalogueDto hospitalThreeCatalogueDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalThreeCatalogueDto.getTradeCode());
        queryRequest.setInputParameter(hospitalThreeCatalogueDto.createJSONObject());
        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<HospitalThreeCatalogueDto> hospitalThreeCatalogueDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                HospitalThreeCatalogueDto threeCatalogueDto = JSON.parseObject(jsonArrayString, HospitalThreeCatalogueDto.class);
                hospitalThreeCatalogueDtoList.add(threeCatalogueDto);
            }
            queryResult.setData(hospitalThreeCatalogueDtoList);
        } else {
            throw new HisException("Request failed or timeout.");
        }

        return queryResult;
    }

    @Override
    public QueryResult<List<ICD10Dto>> getHISHospitalICD10(ICD10Dto icd10Dto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(icd10Dto.getTradeCode());
        queryRequest.setInputParameter(icd10Dto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<ICD10Dto> icd10DtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                ICD10Dto icd10 = JSON.parseObject(jsonArrayString, ICD10Dto.class);
                icd10DtoList.add(icd10);
            }
            queryResult.setData(icd10DtoList);
        } else {
            throw new HisException("Request failed or timeout.");
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<ICD10Dto>> getHISHospitalICD10Rows(ICD10Dto icd10Dto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(icd10Dto.getTradeCode());
        queryRequest.setInputParameter(icd10Dto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<ICD10Dto> icd10DtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                ICD10Dto icd10 = JSON.parseObject(jsonArrayString, ICD10Dto.class);
                icd10DtoList.add(icd10);
            }
            queryResult.setData(icd10DtoList);
        } else {
            throw new HisException("Request failed or timeout.");
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<InpatientDto>> getHISInpatientList(InpatientDto inpatientDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(inpatientDto.getTradeCode());
        queryRequest.setInputParameter(inpatientDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<InpatientDto> inpatientDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                InpatientDto inpatient = JSON.parseObject(jsonArrayString, InpatientDto.class);
                inpatientDtoList.add(inpatient);
            }
            queryResult.setData(inpatientDtoList);
        } else {
            throw new HisException("Request failed or timeout.");
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<OutpatientDto>> getHISOutpatientList(OutpatientDto outpatientDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientDto.getTradeCode());
        queryRequest.setInputParameter(outpatientDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<OutpatientDto> outpatientDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                OutpatientDto outpatient = JSON.parseObject(jsonArrayString, OutpatientDto.class);
                outpatientDtoList.add(outpatient);
            }
            queryResult.setData(outpatientDtoList);
        } else {
            throw new HisException("Request failed or timeout.");
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<HospitalizationSettlementDto>> getHISHospitalizationSettlement(HospitalizationSettlementDto hospitalizationSettlementDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalizationSettlementDto.getTradeCode());
        queryRequest.setInputParameter(hospitalizationSettlementDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<HospitalizationSettlementDto> hospitalizationSettlementDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                HospitalizationSettlementDto hospitalizationSettlement = JSON.parseObject(jsonArrayString, HospitalizationSettlementDto.class);
                hospitalizationSettlementDtoList.add(hospitalizationSettlement);
            }
            queryResult.setData(hospitalizationSettlementDtoList);
        } else {
            throw new HisException("Request failed or timeout.");
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<HospitalizationFeeDto>> getHISHospitalizationFee(HospitalizationFeeDto hospitalizationFeeDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalizationFeeDto.getTradeCode());
        queryRequest.setInputParameter(hospitalizationFeeDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<HospitalizationFeeDto> hospitalizationFeeDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                HospitalizationFeeDto hospitalizationFee = JSON.parseObject(jsonArrayString, HospitalizationFeeDto.class);
                hospitalizationFeeDtoList.add(hospitalizationFee);
            }
            queryResult.setData(hospitalizationFeeDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<OutpatientFeeDto>> getHISOutpatientFee(OutpatientFeeDto outpatientFeeDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientFeeDto.getTradeCode());
        queryRequest.setInputParameter(outpatientFeeDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<OutpatientFeeDto> outpatientFeeDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                OutpatientFeeDto outpatientFee = JSON.parseObject(jsonArrayString, OutpatientFeeDto.class);
                outpatientFeeDtoList.add(outpatientFee);
            }
            queryResult.setData(outpatientFeeDtoList);
        }
        return queryResult;
    }



    @Override
    public QueryResult<List<HospitalOrganizationDto>> getHISHospitalInstitutionDetail( HospitalOrganizationDto hospitalOrganizationDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalOrganizationDto.getTradeCode());
        queryRequest.setInputParameter(hospitalOrganizationDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<HospitalOrganizationDto> hospitalOrganizationDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                HospitalOrganizationDto hospitalInstitution = JSON.parseObject(jsonArrayString, HospitalOrganizationDto.class);
                hospitalOrganizationDtoList.add(hospitalInstitution);
            }
            queryResult.setData(hospitalOrganizationDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<HospitalOrganizationDto>> getHISHospitalInstitutionList( HospitalOrganizationDto hospitalOrganizationDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalOrganizationDto.getTradeCode());
        queryRequest.setInputParameter(hospitalOrganizationDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<HospitalOrganizationDto> hospitalOrganizationDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                HospitalOrganizationDto hospitalInstitution = JSON.parseObject(jsonArrayString, HospitalOrganizationDto.class);
                hospitalOrganizationDtoList.add(hospitalInstitution);
            }
            queryResult.setData(hospitalOrganizationDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<HospitalPaymentDto>> getHISHospitalPaymentList(HospitalPaymentDto hospitalPaymentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalPaymentDto.getTradeCode());
        queryRequest.setInputParameter(hospitalPaymentDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<HospitalPaymentDto> hospitalPaymentDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                HospitalPaymentDto hospitalPayment = JSON.parseObject(jsonArrayString, HospitalPaymentDto.class);
                hospitalPaymentDtoList.add(hospitalPayment);
            }
            queryResult.setData(hospitalPaymentDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<RegistrationFeeTypeDto>> getHISPatientRegistrationList(RegistrationFeeTypeDto registrationFeeTypeDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(registrationFeeTypeDto.getTradeCode());
        queryRequest.setInputParameter(registrationFeeTypeDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<RegistrationFeeTypeDto> registrationFeeTypeDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                RegistrationFeeTypeDto patientRegistration = JSON.parseObject(jsonArrayString, RegistrationFeeTypeDto.class);
                registrationFeeTypeDtoList.add(patientRegistration);
            }
            queryResult.setData(registrationFeeTypeDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<RegistrationTemplateDto>> getHISHospitalRegistrationTemplateList(RegistrationTemplateDto hospitalRegistrationTemplateDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalRegistrationTemplateDto.getTradeCode());
        queryRequest.setInputParameter(hospitalRegistrationTemplateDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<RegistrationTemplateDto> registrationTemplateDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                RegistrationTemplateDto registrationTemplateDto = JSON.parseObject(jsonArrayString, RegistrationTemplateDto.class);
                registrationTemplateDtoList.add(registrationTemplateDto);
            }
            queryResult.setData(registrationTemplateDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<HospitalDepartmentDto>> getHISHospitalRegistrationDepartmentList(HospitalDepartmentDto hospitalDepartmentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalDepartmentDto.getTradeCode());
        queryRequest.setInputParameter(hospitalDepartmentDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<HospitalDepartmentDto> hospitalDepartmentDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                HospitalDepartmentDto hospitalDepartment = JSON.parseObject(jsonArrayString, HospitalDepartmentDto.class);
                hospitalDepartmentDtoList.add(hospitalDepartment);
            }
            queryResult.setData(hospitalDepartmentDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<DoctorDto>> getHISDepartmentDoctorList(DoctorDto doctorDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(doctorDto.getTradeCode());
        queryRequest.setInputParameter(doctorDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<DoctorDto> doctorDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                DoctorDto doctor = JSON.parseObject(jsonArrayString, DoctorDto.class);
                doctorDtoList.add(doctor);
            }
            queryResult.setData(doctorDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<RegistrationDto>> getHISRegistration(RegistrationDto registrationDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(registrationDto.getTradeCode());
        //"缴费方式列表": "[{\"PaymentID\":\"支付方式ID\",\"OrgAccID\":\"账户ID\",\"Fee\",\"金额\"}]"
        List<PayAccountBO> paymentList = registrationDto.getPaymentList();
        String paymentListStr ="";
        StringBuilder sbs = new StringBuilder();
        sbs.append("[");
        for (PayAccountBO payAccountBO:paymentList){
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
        registrationDto.setPaymentListStr(paymentListStr);

        queryRequest.setInputParameter(registrationDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_FAILURE_CODE.equals(queryResult.getResult())) {
            throw new HisException(queryResult.getMsg());
        }
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<RegistrationDto> registrationDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                RegistrationDto registration = JSON.parseObject(jsonArrayString, RegistrationDto.class);
                registrationDtoList.add(registration);
            }
            queryResult.setData(registrationDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<String> getHISWithdrawalRegistration(RegistrationDto registrationDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(registrationDto.getTradeCode());
        queryRequest.setInputParameter(registrationDto.createJSONObject());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            queryResult.setMsg("退号成功!");
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<RegistrationDto>> getHISRegistrationRecord(RegistrationDto registrationDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(registrationDto.getTradeCode());
        queryRequest.setInputParameter(registrationDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<RegistrationDto> registrationDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                RegistrationDto registration = JSON.parseObject(jsonArrayString, RegistrationDto.class);
                registrationDtoList.add(registration);
            }
            queryResult.setData(registrationDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<OutpatientExpensesBillDto>> getHISPrePayOutpatientExpensesBillList(OutpatientExpensesBillDto outpatientExpensesBillDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientExpensesBillDto.getTradeCode());
        queryRequest.setInputParameter(outpatientExpensesBillDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<OutpatientExpensesBillDto> outpatientExpensesBillDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                OutpatientExpensesBillDto outpatientExpensesBill = JSON.parseObject(jsonArrayString, OutpatientExpensesBillDto.class);
                outpatientExpensesBillDtoList.add(outpatientExpensesBill);
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
        String paymentListStr ="";
        StringBuilder sbs = new StringBuilder();
        sbs.append("[");
        for (PayAccountBO payAccountBO:paymentList){
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

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

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

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<OutpatientPaymentDto> outpatientPaymentDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                OutpatientPaymentDto outpatientPayment = JSON.parseObject(jsonArrayString, OutpatientPaymentDto.class);
                outpatientPaymentDtoList.add(outpatientPayment);
            }
            queryResult.setData(outpatientPaymentDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<ExpenseBillDto>> getHISOutpatientBillDetail(ExpenseBillDto expenseBillDto)  throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(expenseBillDto.getTradeCode());
        queryRequest.setInputParameter(expenseBillDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<ExpenseBillDto> expenseBillDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                ExpenseBillDto expenseBill = JSON.parseObject(jsonArrayString, ExpenseBillDto.class);
                expenseBillDtoList.add(expenseBill);
            }
            queryResult.setData(expenseBillDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<OutpatientPaymentDto> getHISOutpatientRefund(OutpatientPaymentDto outpatientPaymentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientPaymentDto.getTradeCode());
        queryRequest.setInputParameter(outpatientPaymentDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            OutpatientPaymentDto outpatientPayment = JSON.parseObject(queryResultMsg, OutpatientPaymentDto.class);
            queryResult.setData(outpatientPayment);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<InpatientDto>> getHISInpatientRecordList(InpatientDto inpatientDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(inpatientDto.getTradeCode());
        queryRequest.setInputParameter(inpatientDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<InpatientDto> inpatientDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                InpatientDto inpatient = JSON.parseObject(jsonArrayString, InpatientDto.class);
                inpatientDtoList.add(inpatient);
            }
            queryResult.setData(inpatientDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<PrepaymentDto> getHISInpatientPrepayment(PrepaymentDto prepaymentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(prepaymentDto.getTradeCode());

        List<PayAccountBO> paymentList = prepaymentDto.getPaymentList();
        String paymentListStr ="";
        StringBuilder sbs = new StringBuilder();
        sbs.append("[");
        for (PayAccountBO payAccountBO:paymentList){
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

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

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

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<PrepaymentDto> prepaymentDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                PrepaymentDto prepayment = JSON.parseObject(jsonArrayString, PrepaymentDto.class);
                prepaymentDtoList.add(prepayment);
            }
            queryResult.setData(prepaymentDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<DailyBillDto>> getHISInpatientDailyBillList(DailyBillDto dailyBillDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(dailyBillDto.getTradeCode());
        queryRequest.setInputParameter(dailyBillDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<DailyBillDto> dailyBillDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                DailyBillDto dailyBill = JSON.parseObject(jsonArrayString, DailyBillDto.class);
                dailyBillDtoList.add(dailyBill);
            }
            queryResult.setData(dailyBillDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<InspectionReportDto> getHISInspectionReportList(InspectionReportDto inspectionReportDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(inspectionReportDto.getTradeCode());
        queryRequest.setInputParameter(inspectionReportDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            InspectionReportDto inspectionReport = JSON.parseObject(queryResultMsg, InspectionReportDto.class);
            queryResult.setData(inspectionReport);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<InspectionApplyFormDto>> getHISInspectionReportApplyForm(InspectionApplyFormDto inspectionApplyFormDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(inspectionApplyFormDto.getTradeCode());
        queryRequest.setInputParameter(inspectionApplyFormDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<InspectionApplyFormDto> inspectionApplyFormDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                InspectionApplyFormDto inspectionApplyForm = JSON.parseObject(jsonArrayString, InspectionApplyFormDto.class);
                inspectionApplyFormDtoList.add(inspectionApplyForm);
            }
            queryResult.setData(inspectionApplyFormDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<DoctorScheduleDto>> getHISDoctorScheduleList(DoctorScheduleDto doctorScheduleDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(doctorScheduleDto.getTradeCode());
        queryRequest.setInputParameter(doctorScheduleDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<DoctorScheduleDto> doctorScheduleDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                DoctorScheduleDto doctorSchedule = JSON.parseObject(jsonArrayString, DoctorScheduleDto.class);
                doctorScheduleDtoList.add(doctorSchedule);
            }
            queryResult.setData(doctorScheduleDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<MedicalOrderDto>> getHISMedicalOrderList(MedicalOrderDto medicalOrderDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(medicalOrderDto.getTradeCode());
        queryRequest.setInputParameter(medicalOrderDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<MedicalOrderDto> medicalOrderDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                MedicalOrderDto medicalOrder = JSON.parseObject(jsonArrayString, MedicalOrderDto.class);
                medicalOrderDtoList.add(medicalOrder);
            }
            queryResult.setData(medicalOrderDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<String> deleteSettlementFeeByBusinessId(CommonDto commonDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(commonDto.getTradeCode());
        queryRequest.setInputParameter(commonDto.createJSONObject());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
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
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
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
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
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
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
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
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
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
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            queryResult.setMsg("信息回写成功!");//返回的msg文本比较长
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<ResidentDto>> getResidentList(ResidentDto residentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(residentDto.getTradeCode());
        queryRequest.setInputParameter(residentDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hisPublicWSClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<ResidentDto> residentDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                ResidentDto resident = JSON.parseObject(jsonArrayString, ResidentDto.class);
                residentDtoList.add(resident);
            }
            queryResult.setData(residentDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<PacsItemDto>> getPacsItemList(PacsItemDto pacsItemDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(pacsItemDto.getTradeCode());
        queryRequest.setInputParameter(pacsItemDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);

            List<PacsItemDto> pacsItemDtoList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                PacsItemDto pacsItem = JSON.parseObject(jsonArrayString, PacsItemDto.class);
                pacsItemDtoList.add(pacsItem);
            }
            queryResult.setData(pacsItemDtoList);
        }
        return queryResult;
    }
}
