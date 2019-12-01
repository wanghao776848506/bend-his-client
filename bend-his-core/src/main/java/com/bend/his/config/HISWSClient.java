package com.bend.his.config;


import com.alibaba.fastjson.JSON;
import com.bend.his.common.CommonPojo;
import com.bend.his.common.DateUtil;
import com.bend.his.common.HISResult;
import com.bend.his.common.ResponseFormat;
import com.bend.his.common.request.QueryRequest;
import com.bend.his.exception.HisException;
import com.bend.his.wsdl.HISInterface;
import com.bend.his.wsdl.HISInterfaceResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


public class HISWSClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(HISWSClient.class);

    public HISInterfaceResponse invokeWebService(QueryRequest queryRequest) throws HisException {
        HISInterface req = new HISInterface();
        req.setTradeCode(queryRequest.getTradeCode());
        req.setInputParameter(queryRequest.getInputParameter());
        //使用 marshalSendAndReceive 进行调用
        return (HISInterfaceResponse) getWebServiceTemplate().marshalSendAndReceive(req);
    }

    public HISResult invokeWebService(CommonPojo commonPojo) throws com.bend.his.common.HisException {
        logger.info("{}-接口交易参数:{}", DateUtil.getCurrentTime(), commonPojo.getInputParameter());
        HISInterface req = new HISInterface();
        req.setTradeCode(commonPojo.getTradeCode());
        req.setInputParameter(commonPojo.getInputParameter());
        //使用 marshalSendAndReceive 进行调用
        HISInterfaceResponse hisInterfaceResponse = (HISInterfaceResponse) getWebServiceTemplate().marshalSendAndReceive(req);
        if (null != hisInterfaceResponse && StringUtils.isNotEmpty(hisInterfaceResponse.getHISInterfaceResult())) {
            String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
            logger.info("{}-接口响应数据:{}", DateUtil.getCurrentTime(), hisInterfaceResult);
            return JSON.parseObject(hisInterfaceResult, HISResult.class);
        } else {
            throw new com.bend.his.common.HisException(ResponseFormat.CODE_60001);
        }
    }
}