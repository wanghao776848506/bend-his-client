package com.bend.his.service.impl;

import com.alibaba.fastjson.JSON;
import com.bend.his.bean.entity.*;
import com.bend.his.bean.vo.*;
import com.bend.his.common.CommonPojo;
import com.bend.his.common.HISResult;
import com.bend.his.common.HisException;
import com.bend.his.common.ResponseFormat;
import com.bend.his.config.HISPublicWSClient;
import com.bend.his.constant.IConstant;
import com.bend.his.service.PublicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("publicService")
public class PublicServiceImpl implements PublicService {

    /**
     * for 公卫
     */
    @Resource
    private HISPublicWSClient hisPublicWSClient;


    @Override
    public PublicAuthDto getHISPublicAuth(CommonPojo<PublicAuthVo> commonPojo) throws HisException {
        HISResult hisResult = hisPublicWSClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseObject(queryResultMsg, PublicAuthDto.class);
        }
    }

    @Override
    public PublicProductDto registerPublicProduct(CommonPojo<PublicProductVo> commonPojo) throws HisException {
        HISResult hisResult = hisPublicWSClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            //查询出错
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseObject(queryResultMsg, PublicProductDto.class);
        }
    }

    @Override
    public List<ResidentBaseInfoDto> getResidentHealthBaseInfoList(CommonPojo<ResidentBaseInfoVo> commonPojo) throws HisException {
        HISResult hisResult = hisPublicWSClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            //查询出错
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, ResidentBaseInfoDto.class);
        }
    }

    @Override
    public List<PersonalHealthCheckupDto> getPersonalHealthCheckupRecordList(CommonPojo<PersonalHealthCheckupVo> commonPojo) throws HisException {
        HISResult hisResult = hisPublicWSClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            //查询出错
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, PersonalHealthCheckupDto.class);
        }
    }

    @Override
    public ResidentHealthFileDto getResidentHealthFile(CommonPojo<ResidentHealthFileVo> commonPojo) throws HisException {
        HISResult hisResult = hisPublicWSClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            //查询出错
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseObject(queryResultMsg, ResidentHealthFileDto.class);
        }
    }

    @Override
    public List<HealthCheckupDateRecordDto> getHealthCheckupDateRecordList(CommonPojo<HealthCheckupDateRecordVo> commonPojo) throws HisException {
        HISResult hisResult = hisPublicWSClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            //查询出错
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, HealthCheckupDateRecordDto.class);
        }
    }

    @Override
    public List<OldHealthCheckupDto> getOldHealthCheckupRecordList(CommonPojo<OldHealthCheckupVo> commonPojo) throws HisException {
        HISResult hisResult = hisPublicWSClient.invokeWebService(commonPojo);
        /*ws服务请求成功验证*/
        if (IConstant.RESULT_FAILURE_CODE.equals(hisResult.getResult())) {
            //查询出错
            throw new HisException(ResponseFormat.CODE_50004, hisResult.getMsg());
        } else {
            String queryResultMsg = hisResult.getMsg();
            return JSON.parseArray(queryResultMsg, OldHealthCheckupDto.class);
        }
    }
}
