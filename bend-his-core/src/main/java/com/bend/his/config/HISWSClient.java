package com.bend.his.config;

import com.alibaba.fastjson.JSON;
import com.bend.his.common.CommonPojo;
import com.bend.his.common.DateUtil;
import com.bend.his.common.HISResult;
import com.bend.his.common.ResponseFormat;
import com.bend.his.exception.HisException;
import com.bend.his.wsdl.HISInterface;
import com.bend.his.wsdl.HISInterfaceResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


public class HISWSClient extends WebServiceGatewaySupport {

    private static final Logger logger = LoggerFactory.getLogger(HISWSClient.class);

    public HISResult invokeWebService(CommonPojo commonPojo) throws HisException {
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
            throw new HisException(ResponseFormat.CODE_60001);
        }
    }
}