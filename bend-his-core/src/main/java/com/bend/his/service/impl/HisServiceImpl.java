package com.bend.his.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bend.his.bean.bo.ExpensesBillBO;
import com.bend.his.bean.bo.InspectionReportBO;
import com.bend.his.bean.bo.InspectionReportItemBO;
import com.bend.his.bean.bo.PayAccountBO;
import com.bend.his.bean.entity.*;
import com.bend.his.common.request.QueryRequest;
import com.bend.his.common.result.QueryResult;
import com.bend.his.config.HISPublicWSClient;
import com.bend.his.config.HISWSClient;
import com.bend.his.constant.DirectoryTypeEnum;
import com.bend.his.constant.IConstant;
import com.bend.his.constant.TradeCode;
import com.bend.his.exception.HisException;
import com.bend.his.service.HisService;
import com.bend.his.wsdl.HISInterfaceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

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
    public QueryResult getHISAuthConnector(AuthenticationDto authenticationDto) throws HisException {
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

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }

        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);
            AuthenticationDto dto = jsonArray.getObject(0, AuthenticationDto.class);
            queryResult.setData(dto);
        }
        return queryResult;
    }

    @Override
    public QueryResult<PublicAuthDto> getHISPublicAuth(PublicAuthDto publicAuthDto) throws HisException {

        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(publicAuthDto.getTradeCode());
        queryRequest.setInputParameter(publicAuthDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hisPublicWSClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            //PublicAuthDto dto = JSON.parseObject(queryResultMsg, PublicAuthDto.class);

            JSONArray jsonArray = JSON.parseArray(queryResultMsg);
            PublicAuthDto dto = jsonArray.getObject(0, PublicAuthDto.class);

            queryResult.setData(dto);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<ComprehensiveCatalogueDto>> getHISComprehensiveCatalogue(ComprehensiveCatalogueDto comprehensiveCatalogueDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(comprehensiveCatalogueDto.getTradeCode());
        queryRequest.setInputParameter(comprehensiveCatalogueDto.createJSONObject());
        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<ComprehensiveCatalogueDto> catalogueDtoList = JSON.parseArray(queryResultMsg, ComprehensiveCatalogueDto.class);
            queryResult.setData(catalogueDtoList);
        }
        return queryResult;
    }

    /**
     * 目录名称（科室、医生、病区、床位）查询
     *
     * @param comprehensiveCatalogueDto
     * @return
     * @throws HisException
     */
    @Override
    public QueryResult<List<ComprehensiveCatalogueDto>> getHISComprehensiveCatalogueByName(ComprehensiveCatalogueDto comprehensiveCatalogueDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(comprehensiveCatalogueDto.getTradeCode());
        queryRequest.setInputParameter(comprehensiveCatalogueDto.createJSONObject());
        String departmentName = comprehensiveCatalogueDto.getDirectoryName();

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);
            List<ComprehensiveCatalogueDto> catalogueDtoList = jsonArray.toJavaList(ComprehensiveCatalogueDto.class);
            //筛选出指定科室数据
            List<ComprehensiveCatalogueDto> departmentList = catalogueDtoList.stream().filter(s -> departmentName.equals(s.getDirectoryName())).collect(Collectors.toList());

            queryResult.setData(departmentList);
        }
        return queryResult;
    }


    /**
     * 1、返回综合目录数据
     * 2、筛选出指定医生数据
     * 3、封装指定医生其他信息
     *
     * @param comprehensiveCatalogueDto
     * @return
     * @throws HisException
     */
    @Override
    public QueryResult<List<ComprehensiveCatalogueDto>> getHISComprehensiveCatalogueByDoctorInfo(ComprehensiveCatalogueDto comprehensiveCatalogueDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(comprehensiveCatalogueDto.getTradeCode());
        queryRequest.setInputParameter(comprehensiveCatalogueDto.createJSONObject());

        String doctorName = comprehensiveCatalogueDto.getDirectoryName();//指定医生的名字
        String directoryType = comprehensiveCatalogueDto.getDirectoryType();//数据类型[0科室、1医生、2病区、3床位]
        String organizationCode = comprehensiveCatalogueDto.getOrganizationCode();//机构编码

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<ComprehensiveCatalogueDto> catalogueDtoList = JSON.parseArray(queryResultMsg, ComprehensiveCatalogueDto.class);
            List<ComprehensiveCatalogueDto> doctorList;
            //筛选出指定医生数据
            if (!StringUtils.isEmpty(doctorName)) {
                doctorList = catalogueDtoList.stream().filter(s -> doctorName.equals(s.getDirectoryName())).collect(Collectors.toList());
            } else {
                doctorList = catalogueDtoList;
            }
            //去重字符串list.stream().distinct()
            //对象去重
            doctorList = doctorList.stream().collect(collectingAndThen(toCollection(() -> new TreeSet<>(Comparator.comparing(ComprehensiveCatalogueDto::getDirectoryCode))), ArrayList::new));

            //查询医生数据,处理备注[目录类型1： 返回医生所在科室的编码;目录类型3： 返回床位所在的病区编码.]
            if (DirectoryTypeEnum.DOCTOR.getValue().equals(directoryType)) {
                if (!CollectionUtils.isEmpty(doctorList)) {
                    for (int i = 0; i < doctorList.size(); i++) {
                        ComprehensiveCatalogueDto dto = doctorList.get(i);
                        String doctorName2 = dto.getDirectoryName(); //医生姓名
                        String doctorId = dto.getDirectoryCode();//医生ID
                        String departmentId = dto.getRemark();//备注--科室ID
                        String departmentName = this.getSectionName(comprehensiveCatalogueDto, departmentId); //科室名称

                        DoctorDto doctorDto = new DoctorDto();
                        doctorDto.setDoctorId(doctorId);
                        doctorDto.setDoctorName(doctorName2);
                        doctorDto.setDepartmentId(departmentId);
                        doctorDto.setDepartmentName(departmentName);

                        dto.setDoctorDto(doctorDto);
                    }
                }
            }
            queryResult.setData(doctorList);
        }
        return queryResult;
    }

    /**
     * 科室名称
     *
     * @param comprehensiveCatalogueDto
     * @param departmentId
     * @return
     * @throws HisException
     */
    private String getSectionName(ComprehensiveCatalogueDto comprehensiveCatalogueDto, String departmentId) throws HisException {
        //查询科室
        comprehensiveCatalogueDto.setDirectoryType(DirectoryTypeEnum.SECTION.getValue());

        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(comprehensiveCatalogueDto.getTradeCode());
        queryRequest.setInputParameter(comprehensiveCatalogueDto.createJSONObject());
        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        List<ComprehensiveCatalogueDto> departmentList = new ArrayList<>();
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);
            List<ComprehensiveCatalogueDto> comprehensiveCatalogueList = jsonArray.toJavaList(ComprehensiveCatalogueDto.class);
            //科室编码唯一,返回一个
            departmentList = comprehensiveCatalogueList.stream().
                    filter(comprehensiveCatalogue -> departmentId.equals(comprehensiveCatalogue.getDirectoryCode())).
                    collect(Collectors.toList());
        }
        return departmentList.get(0).getDirectoryName();
    }

    @Override
    public QueryResult<List<HospitalThreeCatalogueDto>> getHISHospitalThreeCatalogue(HospitalThreeCatalogueDto hospitalThreeCatalogueDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalThreeCatalogueDto.getTradeCode());
        queryRequest.setInputParameter(hospitalThreeCatalogueDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<HospitalThreeCatalogueDto> hospitalThreeCatalogueDtoList = JSON.parseArray(queryResultMsg, HospitalThreeCatalogueDto.class);
            queryResult.setData(hospitalThreeCatalogueDtoList);
        }

        return queryResult;
    }


    @Override
    public QueryResult<List<HospitalThreeCatalogueDto>> getHISHospitalThreeCatalogueRows(HospitalThreeCatalogueDto hospitalThreeCatalogueDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalThreeCatalogueDto.getTradeCode());
        queryRequest.setInputParameter(hospitalThreeCatalogueDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<HospitalThreeCatalogueDto> hospitalThreeCatalogueDtoList = JSON.parseArray(queryResultMsg, HospitalThreeCatalogueDto.class);
            queryResult.setData(hospitalThreeCatalogueDtoList);
        }

        return queryResult;
    }

    @Override
    public QueryResult<List<ICD10Dto>> getHISHospitalICD10(ICD10Dto icd10Dto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(icd10Dto.getTradeCode());
        queryRequest.setInputParameter(icd10Dto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }

        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<ICD10Dto> icd10DtoList = JSON.parseArray(queryResultMsg, ICD10Dto.class);
            queryResult.setData(icd10DtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<ICD10Dto>> getHISHospitalICD10Rows(ICD10Dto icd10Dto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(icd10Dto.getTradeCode());
        queryRequest.setInputParameter(icd10Dto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<ICD10Dto> icd10DtoList = JSON.parseArray(queryResultMsg, ICD10Dto.class);
            queryResult.setData(icd10DtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<InpatientDto>> getHISInpatientList(InpatientDto inpatientDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(inpatientDto.getTradeCode());
        queryRequest.setInputParameter(inpatientDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }

        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<InpatientDto> inpatientDtoList = JSON.parseArray(queryResultMsg, InpatientDto.class);
            queryResult.setData(inpatientDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<OutpatientDto>> getHISOutpatientList(OutpatientDto outpatientDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientDto.getTradeCode());
        queryRequest.setInputParameter(outpatientDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<OutpatientDto> outpatientDtoList = JSON.parseArray(queryResultMsg, OutpatientDto.class);
            queryResult.setData(outpatientDtoList);
        }
        return queryResult;
    }


    @Override
    public QueryResult<List<HospitalizationSettlementDto>> getHISHospitalizationSettlement(HospitalizationSettlementDto hospitalizationSettlementDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalizationSettlementDto.getTradeCode());
        queryRequest.setInputParameter(hospitalizationSettlementDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<HospitalizationSettlementDto> hospitalizationSettlementDtoList = JSON.parseArray(queryResultMsg, HospitalizationSettlementDto.class);
            queryResult.setData(hospitalizationSettlementDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<HospitalizationFeeDto>> getHISHospitalizationFee(HospitalizationFeeDto hospitalizationFeeDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalizationFeeDto.getTradeCode());
        queryRequest.setInputParameter(hospitalizationFeeDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<HospitalizationFeeDto> hospitalizationFeeDtoList = JSON.parseArray(queryResultMsg, HospitalizationFeeDto.class);
            queryResult.setData(hospitalizationFeeDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<OutpatientFeeDto>> getHISOutpatientFee(OutpatientFeeDto outpatientFeeDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(outpatientFeeDto.getTradeCode());
        queryRequest.setInputParameter(outpatientFeeDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<OutpatientFeeDto> outpatientFeeDtoList = JSON.parseArray(queryResultMsg, OutpatientFeeDto.class);
            queryResult.setData(outpatientFeeDtoList);
        }
        return queryResult;
    }


    @Override
    public QueryResult<List<HospitalOrganizationDto>> getHISHospitalInstitutionDetail(HospitalOrganizationDto hospitalOrganizationDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalOrganizationDto.getTradeCode());
        queryRequest.setInputParameter(hospitalOrganizationDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<HospitalOrganizationDto> hospitalOrganizationDtoList = JSON.parseArray(queryResultMsg, HospitalOrganizationDto.class);
            queryResult.setData(hospitalOrganizationDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<HospitalOrganizationDto>> getHISHospitalInstitutionList(HospitalOrganizationDto hospitalOrganizationDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalOrganizationDto.getTradeCode());
        queryRequest.setInputParameter(hospitalOrganizationDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<HospitalOrganizationDto> hospitalOrganizationDtoList = JSON.parseArray(queryResultMsg, HospitalOrganizationDto.class);
            queryResult.setData(hospitalOrganizationDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<HospitalPaymentDto>> getHISHospitalPaymentList(HospitalPaymentDto hospitalPaymentDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(hospitalPaymentDto.getTradeCode());
        queryRequest.setInputParameter(hospitalPaymentDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<HospitalPaymentDto> hospitalPaymentDtoList = JSON.parseArray(queryResultMsg, HospitalPaymentDto.class);
            queryResult.setData(hospitalPaymentDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<RegistrationFeeTypeDto>> getHISPatientRegistrationList(RegistrationFeeTypeDto registrationFeeTypeDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(registrationFeeTypeDto.getTradeCode());
        queryRequest.setInputParameter(registrationFeeTypeDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<RegistrationFeeTypeDto> registrationFeeTypeDtoList = JSON.parseArray(queryResultMsg, RegistrationFeeTypeDto.class);
            queryResult.setData(registrationFeeTypeDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<RegistrationTemplateDto>> getHISHospitalRegistrationTemplateList(RegistrationTemplateDto templateDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(templateDto.getTradeCode());
        queryRequest.setInputParameter(templateDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
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

            List<RegistrationTemplateDto> registrationTemplateDtoList = new ArrayList<>();

            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                RegistrationTemplateDto registrationTemplateDto = JSON.parseObject(jsonArrayString, RegistrationTemplateDto.class);
                registrationTemplateDto.setOrganizationCode(templateDto.getOrganizationCode());
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

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<HospitalDepartmentDto> hospitalDepartmentDtoList = JSON.parseArray(queryResultMsg, HospitalDepartmentDto.class);
            queryResult.setData(hospitalDepartmentDtoList);
        }
        return queryResult;
    }

    @Override
    public List<HospitalDepartmentDto> getHISDepartmentRegistrationTemplateList(HospitalDepartmentDto hospitalDepartmentDto, List<RegistrationTemplateDto> registrationTemplateDtoList) throws HisException {
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
        String departmentName = hospitalDepartmentDto.getDepartmentName();
        if (!StringUtils.isEmpty(departmentName)) {
            List<HospitalDepartmentDto> list = departmentAllList.stream().filter(s -> s.getDepartmentName().contains(departmentName)).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(list)) {
                departmentAllList = list;
            }
        }
        //科室ID精确查找
        String departmentId = hospitalDepartmentDto.getDepartmentId();
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
            for (int i = 0; i < v.size(); i++) {
                tmpList.add(v.get(i).getRegistrationTemplateDto());
            }
            hospitalDepartmentDto.setRegistrationTemplateList(tmpList);
            newList.add(hospitalDepartmentDto);

        });
        //newList.stream().forEach(System.out::println);

        return newList;
    }

    private List<HospitalDepartmentDto> getHISRegistrationDepartmentList(RegistrationTemplateDto registrationTemplate) throws HisException {

        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(TradeCode.TRADE_30_2);//查询挂号模板下科室
        queryRequest.setInputParameter(registrationTemplate.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        List<HospitalDepartmentDto> departmentDtoList = new ArrayList<>();
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            JSONArray jsonArray = JSON.parseArray(queryResultMsg);
            for (int i = 0; i < jsonArray.size(); i++) {
                String jsonArrayString = jsonArray.getString(i);
                HospitalDepartmentDto departmentDto = JSON.parseObject(jsonArrayString, HospitalDepartmentDto.class);
                //设置挂号模板(当前的)
                departmentDto.setRegistrationTemplateDto(registrationTemplate);
                departmentDtoList.add(departmentDto);
            }
        }
        return departmentDtoList;
    }

    @Override
    public QueryResult<List<DoctorDto>> getHISDepartmentDoctorList(DoctorDto doctorDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(doctorDto.getTradeCode());
        queryRequest.setInputParameter(doctorDto.createJSONObject());

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
            List<DoctorDto> doctorDtoList = JSON.parseArray(queryResultMsg, DoctorDto.class);
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
        registrationDto.setPaymentListStr(paymentListStr);

        queryRequest.setInputParameter(registrationDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse = null;
        try {
            hisInterfaceResponse = hiswsClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);
        if (IConstant.RESULT_FAILURE_CODE.equals(queryResult.getResult())) {
            throw new HisException(queryResult.getMsg());
        }
        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<RegistrationDto> registrationDtoList = JSON.parseArray(queryResultMsg, RegistrationDto.class);
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
            List<OutpatientExpensesBillDto> outpatientExpensesBillDtoList = JSON.parseArray(queryResultMsg, OutpatientExpensesBillDto.class);
            if (!CollectionUtils.isEmpty(outpatientExpensesBillDtoList)){
                for (OutpatientExpensesBillDto dto : outpatientExpensesBillDtoList){
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
            List<InpatientDto> inpatientDtoList = JSON.parseArray(queryResultMsg, InpatientDto.class);
            queryResult.setData(inpatientDtoList);
        }
        return queryResult;
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

    @Deprecated
    @Override
    public QueryResult<List<ResidentBaseInfoDto>> getResidentList(ResidentBaseInfoDto residentBaseInfoDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(residentBaseInfoDto.getTradeCode());
        queryRequest.setInputParameter(residentBaseInfoDto.createJSONObject());

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
            List<ResidentBaseInfoDto> residentBaseInfoDtoList = JSON.parseArray(queryResultMsg, ResidentBaseInfoDto.class);
            queryResult.setData(residentBaseInfoDtoList);
        }
        return queryResult;
    }

    @Deprecated
    @Override
    public QueryResult<List<PersonalHealthCheckupDto>> getPersonalHealthCheckupRecordList(PersonalHealthCheckupDto personalHealthCheckupDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(personalHealthCheckupDto.getTradeCode());
        queryRequest.setInputParameter(personalHealthCheckupDto.createJSONObject());

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
            List<PersonalHealthCheckupDto> personalHealthCheckupDtoList = JSON.parseArray(queryResultMsg, PersonalHealthCheckupDto.class);
            queryResult.setData(personalHealthCheckupDtoList);
        }
        return queryResult;
    }

    @Deprecated
    @Override
    public QueryResult<ResidentHealthFileDto> getResidentHealthFile(ResidentHealthFileDto residentHealthFileDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(residentHealthFileDto.getTradeCode());
        queryRequest.setInputParameter(residentHealthFileDto.createJSONObject());

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
            ResidentHealthFileDto residentHealthFile = JSON.parseObject(queryResultMsg, ResidentHealthFileDto.class);
            queryResult.setData(residentHealthFile);
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
            List<PacsItemDto> pacsItemDtoList = JSON.parseArray(queryResultMsg, PacsItemDto.class);
            queryResult.setData(pacsItemDtoList);
        }
        return queryResult;
    }
}
