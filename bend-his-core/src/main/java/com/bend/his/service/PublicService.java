package com.bend.his.service;

import com.bend.his.bean.entity.*;
import com.bend.his.bean.vo.*;
import com.bend.his.common.CommonPojo;
import com.bend.his.common.PageResult;
import com.bend.his.exception.HisException;

import java.util.List;

/**
 * 公卫接口
 */
public interface PublicService {

    /**
     * 01 公卫登录 授权接口
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    PublicAuthDto getHISPublicAuth(CommonPojo<PublicAuthVo> commonPojo) throws HisException;

    /**
     * 48-1 注册接口产品如 健康小屋、健康一体机、APP终端等，调用接口的终端都定义为“产品”；每个终端产品需绑定机构和调用接口编号的授权
     *
     * @param commonPojo
     * @return
     * @throws HisException
     */
    PublicProductDto registerPublicProduct(CommonPojo<PublicProductVo> commonPojo) throws HisException;

    /**
     * 55-11 查询居民健康档案基本信息(综合查询)<br>
     * <p>
     * 公卫接口文档地址：http://47.111.29.88:11004/publicdoc/index.php?s=/phisif&page_id=53<br>
     *
     * @param commonPojo 居民健康档案基本信息 - 查询参数,主要参数有：区划编码,居民身份证,居民姓名,分页等
     * @return 居民健康档案基本信息 : (综合查询)不同参数返回不同结果,
     * @throws HisException
     */
    PageResult<ResidentBaseInfoDto> getResidentHealthBaseInfoList(CommonPojo<ResidentBaseInfoVo> commonPojo) throws HisException;

    /**
     * 56-1 查询个人健康体检记录列表(接口55-11返回居民ID ==>> personId)
     *
     * @param commonPojo 个人健康体检记录参数
     * @return 个人健康体检记录列表
     * @throws HisException
     */
    PageResult<PersonalHealthCheckupDto> getPersonalHealthCheckupRecordList(CommonPojo<PersonalHealthCheckupVo> commonPojo) throws HisException;

    /**
     * 56-4 查询个人健康体检记录列表 (通过 接口56-1 返回 mtId ==>> 随访id)
     *
     * @param commonPojo 居民个人健康体检记录
     * @return 个人健康体检记录(多项列表值)
     * @throws HisException
     */
    ResidentHealthFileDto getResidentHealthFile(CommonPojo<ResidentHealthFileVo> commonPojo) throws HisException;


    /**
     * 56-6 查询个人健康体检记录日期(通过personid --> 接口55-11返回居民ID)
     *
     * @param commonPojo 个人健康体检记录日期列表
     * @return 体检记录日期列表
     * @throws HisException
     */
    List<HealthCheckupDateRecordDto> getHealthCheckupDateRecordList(CommonPojo<HealthCheckupDateRecordVo> commonPojo) throws HisException;


    /**
     * 56-8 老年人体检查询
     *
     * @param commonPojo 老年人体检查询
     * @return 老年人体检查询
     * @throws HisException
     */
    List<OldHealthCheckupDto> getOldHealthCheckupRecordList(CommonPojo<OldHealthCheckupVo> commonPojo) throws HisException;
}
