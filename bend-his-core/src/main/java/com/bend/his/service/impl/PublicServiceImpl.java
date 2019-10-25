package com.bend.his.service.impl;

import com.alibaba.fastjson.JSON;
import com.bend.his.bean.entity.*;
import com.bend.his.common.request.QueryRequest;
import com.bend.his.common.result.QueryResult;
import com.bend.his.config.HISPublicWSClient;
import com.bend.his.constant.IConstant;
import com.bend.his.exception.HisException;
import com.bend.his.service.PublicService;
import com.bend.his.wsdl.HISInterfaceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("publicService")
@Slf4j
public class PublicServiceImpl implements PublicService {
    /**
     * for 公卫
     */
    @Resource
    private HISPublicWSClient hisPublicWSClient;


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
            PublicAuthDto dto = JSON.parseObject(queryResultMsg, PublicAuthDto.class);
            queryResult.setData(dto);
        }
        return queryResult;
    }

    @Override
    public QueryResult<PublicProductDto> registerPublicProduct(PublicProductDto publicProductDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(publicProductDto.getTradeCode());
        queryRequest.setInputParameter(publicProductDto.createJSONObject());
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
            PublicProductDto dto = JSON.parseObject(queryResultMsg, PublicProductDto.class);
            queryResult.setData(dto);
        }
        return queryResult;
    }

    @Override
    public QueryResult<PublicAuthDto> getOrgInfoByUserName(PublicAuthDto publicAuthDto) throws HisException {
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
            PublicAuthDto dto = JSON.parseObject(queryResultMsg, PublicAuthDto.class);
            queryResult.setData(dto);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<CustomDictDto>> getCustomDictTypeList(CustomDictDto customDictDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(customDictDto.getTradeCode());
        queryRequest.setInputParameter(customDictDto.createJSONObject());
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
            List<CustomDictDto> customDictDtoList = JSON.parseArray(queryResultMsg, CustomDictDto.class);
            queryResult.setData(customDictDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<ResidentBaseInfoDto>> getResidentHealthBaseInfoList(ResidentBaseInfoDto residentBaseInfoDto) throws HisException {
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

    @Override
    public QueryResult<List<PersonalHealthCheckupDto>> getPersonalHealthCheckupRecordList(PersonalHealthCheckupDto personalHealthCheckupDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(personalHealthCheckupDto.getTradeCode());
        queryRequest.setInputParameter(personalHealthCheckupDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
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

    @Override
    public QueryResult<ResidentHealthFileDto> getResidentHealthFile(ResidentHealthFileDto residentHealthFileDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(residentHealthFileDto.getTradeCode());
        queryRequest.setInputParameter(residentHealthFileDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
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
    public QueryResult<List<HealthCheckupDateRecordDto>> getHealthCheckupDateRecordList(HealthCheckupDateRecordDto healthCheckupDateRecordDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(healthCheckupDateRecordDto.getTradeCode());
        queryRequest.setInputParameter(healthCheckupDateRecordDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hisPublicWSClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<HealthCheckupDateRecordDto> healthCheckupDateRecordDtoList = JSON.parseArray(queryResultMsg, HealthCheckupDateRecordDto.class);
            queryResult.setData(healthCheckupDateRecordDtoList);
        }
        return queryResult;
    }

    @Override
    public QueryResult<List<OldHealthCheckupDto>> getOldHealthCheckupRecordList(OldHealthCheckupDto oldHealthCheckupDto) throws HisException {
        QueryRequest queryRequest = QueryRequest.newBuilder().build();
        queryRequest.setTradeCode(oldHealthCheckupDto.getTradeCode());
        queryRequest.setInputParameter(oldHealthCheckupDto.createJSONObject());

        HISInterfaceResponse hisInterfaceResponse;
        try {
            hisInterfaceResponse = hisPublicWSClient.invokeWebService(queryRequest);
        } catch (HisException e) {
            throw new HisException("Request failed or timeout.");
        }
        String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
        QueryResult queryResult = JSON.parseObject(hisInterfaceResult, QueryResult.class);

        if (IConstant.RESULT_SUCCESS_CODE.equals(queryResult.getResult())) {
            String queryResultMsg = queryResult.getMsg();
            List<OldHealthCheckupDto> healthCheckupDtoList = JSON.parseArray(queryResultMsg, OldHealthCheckupDto.class);
            queryResult.setData(healthCheckupDtoList);
        }
        return queryResult;
    }
}
