package com.bend.his.config;


import com.bend.his.common.request.QueryRequest;
import com.bend.his.wsdl.HISInterface;
import com.bend.his.wsdl.HISInterfaceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


@Slf4j
public class HISWSClient extends WebServiceGatewaySupport {

    public HISInterfaceResponse invokeWebService(QueryRequest queryRequest) {
        HISInterface req = new HISInterface();
        req.setTradeCode(queryRequest.getTradeCode());
        req.setInputParameter(queryRequest.getInputParameter());
        //使用 marshalSendAndReceive 进行调用
        return (HISInterfaceResponse) getWebServiceTemplate().marshalSendAndReceive(req);
    }
}