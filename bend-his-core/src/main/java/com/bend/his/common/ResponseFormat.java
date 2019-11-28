package com.bend.his.common;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 统一返回客户端数据格式
 */
public class ResponseFormat {
    public static final Integer CODE_200 = 200;
    public static final Integer CODE_1000 = 1000;
    public static final Integer CODE_10001 = 10001;
    public static final Integer CODE_10002 = 10002;
    public static final Integer CODE_10003 = 10003;
    public static final Integer CODE_10004 = 10004;
    public static final Integer CODE_20001 = 20001;
    public static final Integer CODE_20002 = 20002;
    public static final Integer CODE_20003 = 20003;
    public static final Integer CODE_20004 = 20004;
    public static final Integer CODE_20005 = 20005;
    public static final Integer CODE_30001 = 30001;
    public static final Integer CODE_40001 = 40001;
    public static final Integer CODE_50001 = 50001;
    public static final Integer CODE_50002 = 50002;
    public static final Integer CODE_50003 = 50003;
    public static final Integer CODE_50004 = 50004;
    public static final Integer CODE_60001 = 60001;
    public static final Integer CODE_60002 = 60002;
    public static final Integer CODE_60003 = 60003;
    public static final Integer CODE_60004 = 60004;
    public static final Integer CODE_60005 = 60005;
    public static final Integer CODE_60006 = 60006;
    public static final Integer CODE_70001 = 70001;


    private static Map<Integer, String> messageMap = Maps.newHashMap();

    //初始化状态码与文字说明
    static {
        /* 成功状态码 */
        messageMap.put(CODE_200, "成功");

        /* 服务器错误 */
        messageMap.put(CODE_1000, "服务器错误");

        /* 参数错误：10001-19999 */
        messageMap.put(CODE_10001, "参数无效");
        messageMap.put(CODE_10002, "参数为空");
        messageMap.put(CODE_10003, "参数类型错误");
        messageMap.put(CODE_10004, "参数缺失");

        /* 用户错误：20001-29999*/
        messageMap.put(CODE_20001, "用户未登录");
        messageMap.put(CODE_20002, "账号不存在或密码错误");
        messageMap.put(CODE_20003, "账号已被禁用");
        messageMap.put(CODE_20004, "用户不存在");
        messageMap.put(CODE_20005, "用户已存在");

        /* 业务错误：30001-39999 */
        messageMap.put(CODE_30001, "某业务出现问题");

        /* 系统错误：40001-49999 */
        messageMap.put(CODE_40001, "系统繁忙，请稍后重试");

        /* 数据错误：50001-599999 */
        messageMap.put(CODE_50001, "数据未找到");
        messageMap.put(CODE_50002, "数据有误");
        messageMap.put(CODE_50003, "数据已存在");
        messageMap.put(CODE_50004, "查询出错");

        /* 接口错误：60001-69999 */
        messageMap.put(CODE_60001, "内部系统接口调用异常");
        messageMap.put(CODE_60002, "外部系统接口调用异常");
        messageMap.put(CODE_60003, "该接口禁止访问");
        messageMap.put(CODE_60004, "接口地址无效");
        messageMap.put(CODE_60005, "接口请求超时");
        messageMap.put(CODE_60006, "接口负载过高");

        /* 权限错误：70001-79999 */
        messageMap.put(CODE_70001, "无权限访问");
    }

    /**
     * 正常数据返回
     *
     * @param data 返回数据
     * @return 正常数据返回
     */
    public static GenericResponse retInfo(Object data) {
        return new GenericResponse(CODE_200, messageMap.get(CODE_200), data);
    }

    /**
     *
     * @param status
     * @return
     */
    public static GenericResponse retInfo(Integer status) {
        return new GenericResponse(status, messageMap.get(status), null);
    }

    /**
     * 返回带状态数据(默认异常提示信息)
     *
     * @param status 状态
     * @param data   数据
     * @return 返回带状态数据
     */
    public static GenericResponse retInfo(Integer status, Object data) {
        return new GenericResponse(status, messageMap.get(status), data);
    }


    /**
     * 返回 带状态和自定义异常信息 数据
     *
     * @param status  状态
     * @param message 异常信息
     * @param data    数据
     * @return 返回 带状态和异常信息 数据
     */
    public static GenericResponse retInfo(Integer status, String message, Object data) {
        return new GenericResponse(status, message, data);
    }


}