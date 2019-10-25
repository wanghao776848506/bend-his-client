package com.bend.his.service;

import com.bend.his.bean.entity.*;
import com.bend.his.common.result.QueryResult;
import com.bend.his.exception.HisException;

import java.util.List;

/**
 *
 * 公卫接口
 *
 */
public interface PublicService {

    /**
     * 01 公卫登录 授权接口
     *
     * @param publicAuthDto
     * @return
     * @throws HisException
     */
    QueryResult<PublicAuthDto> getHISPublicAuth(PublicAuthDto publicAuthDto) throws HisException;

    /**
     * 48-1 注册接口产品如 健康小屋、健康一体机、APP终端等，调用接口的终端都定义为“产品”；每个终端产品需绑定机构和调用接口编号的授权
     *
     * @param publicProductDto
     * @return
     * @throws HisException
     */
    QueryResult<PublicProductDto> registerPublicProduct(PublicProductDto publicProductDto) throws HisException;

    /**
     * 该接口可以通过用户名和密码获取到操作用户所在机构信息
     *
     * @param publicAuthDto
     * @return
     * @throws HisException
     */
    QueryResult<PublicAuthDto> getOrgInfoByUserName(PublicAuthDto publicAuthDto) throws HisException;

    /**
     * 48-3 获取自定义字典
     *
     * @param customDictDto
     * @return
     * @throws HisException
     */
    @Deprecated
    QueryResult<List<CustomDictDto>> getCustomDictTypeList(CustomDictDto customDictDto) throws HisException;


    /**
     * 55-11 查询居民健康档案基本信息(综合查询)<br>
     * <p>
     * 公卫接口文档地址：http://47.111.29.88:11004/publicdoc/index.php?s=/phisif&page_id=53<br>
     *
     * @param residentBaseInfoDto 居民健康档案基本信息 - 查询参数,主要参数有：区划编码,居民身份证,居民姓名,分页等
     * @return 居民健康档案基本信息 : (综合查询)不同参数返回不同结果,
     * @throws HisException
     */
    QueryResult<List<ResidentBaseInfoDto>> getResidentHealthBaseInfoList(ResidentBaseInfoDto residentBaseInfoDto) throws HisException;

    /**
     * 56-1 查询个人健康体检记录列表(接口55-11返回居民ID ==>> personId)
     *
     * @param personalHealthCheckupDto 个人健康体检记录参数
     * @return 个人健康体检记录列表
     * @throws HisException
     */
    QueryResult<List<PersonalHealthCheckupDto>> getPersonalHealthCheckupRecordList(PersonalHealthCheckupDto personalHealthCheckupDto) throws HisException;

    /**
     * 56-4 查询个人健康体检记录列表 (通过 接口56-1 返回 mtId ==>> 随访id)
     *
     * @param residentHealthFileDto 居民个人健康体检记录
     * @return 个人健康体检记录(多项列表值)
     * @throws HisException
     */
    QueryResult<ResidentHealthFileDto> getResidentHealthFile(ResidentHealthFileDto residentHealthFileDto) throws HisException;


    /**
     * 56-6 查询个人健康体检记录日期(通过personid --> 接口55-11返回居民ID)
     *
     * @param healthCheckupDateRecordDto 个人健康体检记录日期列表
     * @return 体检记录日期列表
     * @throws HisException
     */
    QueryResult<List<HealthCheckupDateRecordDto>> getHealthCheckupDateRecordList(HealthCheckupDateRecordDto healthCheckupDateRecordDto) throws HisException;


    /**
     * 56-8 老年人体检查询
     *
     * @param oldHealthCheckupDto 老年人体检查询
     * @return 老年人体检查询
     * @throws HisException
     */
    QueryResult<List<OldHealthCheckupDto>> getOldHealthCheckupRecordList(OldHealthCheckupDto oldHealthCheckupDto) throws HisException;
}
