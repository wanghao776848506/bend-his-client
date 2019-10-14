package com.bend.his.web.controller;

import com.bend.his.exception.HisException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "医保接口", description = "医保接口", tags = {"医保接口"})
@Controller
@RequestMapping(value = "/ybsp/his/insurance", method = RequestMethod.POST)
public class InsuranceController {

    @ApiOperation(value = "医保刷卡", httpMethod = "GET")
    @RequestMapping(value = "card", method = RequestMethod.GET)
    public String readCard() throws HisException {

        return "card";
    }
}
