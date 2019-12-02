package com.bend.his.web.controller;

import com.bend.his.bean.entity.*;
import com.bend.his.bean.vo.*;
import com.bend.his.common.CommonPojo;
import com.bend.his.common.GenericResponse;
import com.bend.his.common.PageResult;
import com.bend.his.common.ResponseFormat;
import com.bend.his.constant.TradeCode;
import com.bend.his.exception.HisException;
import com.bend.his.service.PublicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * http://47.111.29.88:11004/publicdoc/index.php?s=/phisif&page_id=53
 */
@Api(value = "公卫接口", description = "公卫接口", tags = {"公卫接口"})
@RestController
public class PublicController {

    @Resource
    private PublicService publicService;


    @ApiOperation(value = "01 登录验证", position = 1, notes = "此接口用于医院用户登录或医保报账客户端的安全验证，用户名与密码由HIS系统统一分配")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_01),
            @ApiImplicitParam(name = "userName", value = "账号名/用户名"),
            @ApiImplicitParam(name = "password", value = "密码"),
            @ApiImplicitParam(name = "authCode", value = "产品验证码")
    })
    @PostMapping("public/auth")
    public GenericResponse getHISPublicAuth(@RequestBody CommonPojo<PublicAuthVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            PublicAuthVo publicAuthVo = commonPojo.getData();
            commonPojo.setInputParameter(publicAuthVo.getInputParameter());
            PublicAuthDto publicAuthDto = publicService.getHISPublicAuth(commonPojo);
            return ResponseFormat.retInfo(publicAuthDto);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    @ApiOperation(value = "48-1 注册产品", position = 2, notes = "注册接口产品如 健康小屋、健康一体机、APP终端等，调用接口的终端都定义为“产品”；每个终端产品需绑定机构和调用接口编号的授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_48_1),
            @ApiImplicitParam(name = "userName", value = "用户名 -- 是基卫系统登录用户姓名"),
            @ApiImplicitParam(name = "password", value = "密码 -- 是基卫系统登录用户密码"),
            @ApiImplicitParam(name = "companyCode", value = "厂商代码(联系信息中心获取)"),
            @ApiImplicitParam(name = "productType", value = "产品类型: 02，健康小屋；03，健康一体机；04,APP应用；05，其他"),
            @ApiImplicitParam(name = "productName", value = "终端产品名称"),
            @ApiImplicitParam(name = "extInfo", value = "终端唯一识别号"),
    })
    @PostMapping("public/register")
    public GenericResponse registerPublicProduct(@RequestBody CommonPojo<PublicProductVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            PublicProductVo publicProductVo = commonPojo.getData();
            commonPojo.setInputParameter(publicProductVo.getInputParameter());
            PublicProductDto publicProductDto = publicService.registerPublicProduct(commonPojo);
            return ResponseFormat.retInfo(publicProductDto);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 55-11 查询居民健康档案基本信息(综合查询)
     *
     * @param commonPojo 居民健康档案基本信息 - 查询参数,主要参数有：区划编码,居民身份证,居民姓名,分页等
     * @return 居民健康档案基本信息 : (综合查询)不同参数返回不同结果,
     * @throws HisException
     */
    @ApiOperation(value = "55-11 查询居民健康档案基本信息(综合查询)", position = 3, notes = "查询居民健康档案基本信息(综合查询)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_55_11),
            @ApiImplicitParam(name = "authCode", value = "验证码/产品验证码"),
            @ApiImplicitParam(name = "idCardNo", value = "居民身份证"),
            @ApiImplicitParam(name = "name", value = "居民姓名"),
            @ApiImplicitParam(name = "regionCode", value = "区划编码"),
            @ApiImplicitParam(name = "status", value = "状态,默认为活动"),
            @ApiImplicitParam(name = "personCode", value = "档案号"),
            @ApiImplicitParam(name = "personType", value = "居民类型[01 一般人群,02 慢病疾病人群,03 老年人,04 0-6岁儿童,05 孕产妇]"),
            @ApiImplicitParam(name = "typeValue", value = "居民类型值"),
            @ApiImplicitParam(name = "pageIndex", value = "分页索引(0~)"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小(1~100)"),
    })
    @PostMapping("public/personal/base/info")
    public GenericResponse getResidentHealthBaseInfoList(@RequestBody CommonPojo<ResidentBaseInfoVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            ResidentBaseInfoVo residentBaseInfoVo = commonPojo.getData();
            commonPojo.setInputParameter(residentBaseInfoVo.getInputParameter());
            PageResult<ResidentBaseInfoDto> result = publicService.getResidentHealthBaseInfoList(commonPojo);
            return ResponseFormat.retInfo(result);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 56-1 查询个人健康体检记录列表(接口55-11返回居民ID ==>> personId)
     *
     * @param commonPojo PersonalHealthCheckupVo 个人健康体检记录参数
     * @return 个人健康体检记录列表
     * @throws HisException
     */
    @ApiOperation(value = "56-1 查询个人健康体检记录列表(接口55-11返回居民ID)", position = 4, notes = "查询个人健康体检记录列表(接口55-11返回居民ID)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_56_1),
            @ApiImplicitParam(name = "authCode", value = "验证码/产品验证码"),
            @ApiImplicitParam(name = "personId", value = "居民ID"),
            @ApiImplicitParam(name = "organizationCode", value = "机构ID/编码"),
            @ApiImplicitParam(name = "pageIndex", value = "分页索引(0~)"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小(1~100)")
    })
    @PostMapping("public/personal/health/checkup/list")
    public GenericResponse getPersonalHealthCheckupRecordList(@RequestBody CommonPojo<PersonalHealthCheckupVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            PersonalHealthCheckupVo personalHealthCheckupVo = commonPojo.getData();
            commonPojo.setInputParameter(personalHealthCheckupVo.getInputParameter());
            PageResult<PersonalHealthCheckupDto> result = publicService.getPersonalHealthCheckupRecordList(commonPojo);
            return ResponseFormat.retInfo(result);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
//        map.put("total", result.getTotal());
//        map.put("data", result.getData());
    }

    /**
     * 56-4 查询个人健康体检记录列表 (通过 接口56-1 返回 OLDPEOPLEID/mtId ==>> 随访id)
     *
     * @param commonPojo ResidentHealthFileVo 居民个人健康体检记录
     * @return 个人健康体检记录(多项列表值)
     * @throws HisException
     */
    @ApiOperation(value = "56-4 查询个人健康体检记录(随访ID) ", position = 5, notes = "查询个人健康体检记录(接口56-1返回随访ID-mtId)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_56_4),
            @ApiImplicitParam(name = "authCode", value = "验证码/产品验证码"),
            @ApiImplicitParam(name = "mtId", value = "随访ID")
    })
    @PostMapping("public/personal/health/file")
    public GenericResponse getResidentHealthFile(@RequestBody CommonPojo<ResidentHealthFileVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            ResidentHealthFileVo residentHealthFileVo = commonPojo.getData();
            commonPojo.setInputParameter(residentHealthFileVo.getInputParameter());
            ResidentHealthFileDto result = publicService.getResidentHealthFile(commonPojo);
            return ResponseFormat.retInfo(result);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }


    /**
     * 56-6 查询个人健康体检记录日期(通过person_id --> 接口55-11返回居民ID)
     *
     * @param commonPojo 个人健康体检记录日期列表
     * @return
     * @throws HisException
     */
    @ApiOperation(value = "56-6 查询个人健康体检记录日期列表(通过接口55-11返回居民ID) ", position = 6, notes = "查询个人健康体检记录日期列表(通过接口55-11返回居民ID)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_56_6),
            @ApiImplicitParam(name = "authCode", value = "验证码/产品验证码"),
            @ApiImplicitParam(name = "personId", value = "居民ID"),
    })
    @PostMapping("public/personal/health/date/record")
    public GenericResponse getHealthCheckupDateRecordList(@RequestBody CommonPojo<HealthCheckupDateRecordVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            HealthCheckupDateRecordVo healthCheckupDateRecordVo = commonPojo.getData();
            commonPojo.setInputParameter(healthCheckupDateRecordVo.getInputParameter());
            List<HealthCheckupDateRecordDto> result = publicService.getHealthCheckupDateRecordList(commonPojo);
            return ResponseFormat.retInfo(result);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     * 56-8 老年人体检查询
     *
     * @param commonPojo OldHealthCheckupVo 老年人体检查询
     * @return 老年人体检查询
     * @throws HisException
     */
    @ApiOperation(value = "56-8 老年人体检查询", position = 7, notes = "老年人体检查询(65岁以上)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tradeCode", value = "交易编号" + TradeCode.TRADE_56_8),
            @ApiImplicitParam(name = "authCode", value = "验证码/产品验证码"),
            @ApiImplicitParam(name = "pageIndex", value = "分页索引(0~)(必填)"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小(1~100)(必填)"),
            @ApiImplicitParam(name = "regionCode", value = "区划编码(必填)"),
            @ApiImplicitParam(name = "isPerfect", value = "是否完善 0:是 1:否 (不填则查全部)"),
            @ApiImplicitParam(name = "followUpDateS", value = "随访开始时间"),
            @ApiImplicitParam(name = "followUpDateE", value = "随访结束时间"),
            @ApiImplicitParam(name = "nextFollowUpDateS", value = "下次随访开始时间(yyyy-MM-dd)"),
            @ApiImplicitParam(name = "nextFollowUpDateE", value = "下次随访结束时间"),
            @ApiImplicitParam(name = "doctorID", value = "随访医生ID"),
            @ApiImplicitParam(name = "keyValueType", value = "1:姓名或拼音 2:身份证号 3:档案号 (不填则不用填写KeyValue中内容)"),
            @ApiImplicitParam(name = "keyValue", value = "与KeyValueType绑定内容"),
            @ApiImplicitParam(name = "manageRegionCodeList", value = "管辖区划列表"),
    })
    @PostMapping("public/old/health/checkup/list")
    public GenericResponse getOldHealthCheckupRecordList(@RequestBody CommonPojo<OldHealthCheckupVo> commonPojo) throws HisException {
        if (null != commonPojo && null != commonPojo.getData()) {
            OldHealthCheckupVo oldHealthCheckupVo = commonPojo.getData();
            commonPojo.setInputParameter(oldHealthCheckupVo.getInputParameter());
            List<OldHealthCheckupDto> result = publicService.getOldHealthCheckupRecordList(commonPojo);
            return ResponseFormat.retInfo(result);
        } else {
            return ResponseFormat.retInfo(ResponseFormat.CODE_10004);
        }
    }

    /**
     55-11 查询居民健康档案基本信息(综合查询)
     56-1 查询个人健康体检记录列表(personId)
     56-4 查询个人健康体检记录列表(随访id)
     ==>> 查询个人健康体检记录
     */

}
