package com.bend.his.config;


import com.alibaba.fastjson.JSON;
import com.bend.his.common.CommonPojo;
import com.bend.his.common.HISResult;
import com.bend.his.common.HisException;
import com.bend.his.common.ResponseFormat;
import com.bend.his.common.request.QueryRequest;
import com.bend.his.wsdl.HISInterface;
import com.bend.his.wsdl.HISInterfaceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


public class HISPublicWSClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(HISPublicWSClient.class);

    public HISResult invokeWebService(CommonPojo commonPojo) throws HisException {
        logger.debug("接口交易参数:{}", commonPojo.getInputParameter());
        HISInterface req = new HISInterface();
        req.setTradeCode(commonPojo.getTradeCode());
        req.setInputParameter(commonPojo.getInputParameter());
        //使用 marshalSendAndReceive 进行调用
        HISInterfaceResponse hisInterfaceResponse = (HISInterfaceResponse) getWebServiceTemplate().marshalSendAndReceive(req);
        if (null != hisInterfaceResponse && hisInterfaceResponse.getHISInterfaceResult() != "") {
            String hisInterfaceResult = hisInterfaceResponse.getHISInterfaceResult();
            logger.info("接口响应数据:{}", hisInterfaceResult);
            return JSON.parseObject(hisInterfaceResult, HISResult.class);
        } else {
            throw new HisException(ResponseFormat.CODE_60001);
        }
    }

    public HISInterfaceResponse invokeWebService(QueryRequest queryRequest) throws HisException {
        HISInterface req = new HISInterface();
        req.setTradeCode(queryRequest.getTradeCode());
        req.setInputParameter(queryRequest.getInputParameter());
        //使用 marshalSendAndReceive 进行调用
        return (HISInterfaceResponse) getWebServiceTemplate().marshalSendAndReceive(req);
    }
}