package com.bend.his.web.controller;

import com.bend.his.exception.HisException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Api(description = "医保接口", value = "", tags = {"医保接口"})
@RequestMapping(value = "/ybsp/his/insurance")
@Controller
public class InsuranceController {

    @ApiOperation(value = "医保刷卡", position = 1, notes = "")
    @ApiImplicitParams({})
    @RequestMapping("card")
    public String readCard() throws HisException {

        return "card";
    }
}
