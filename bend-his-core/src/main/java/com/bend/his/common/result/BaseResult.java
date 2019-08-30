package com.bend.his.common.result;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * lombok的getter和@JSONField 无法进行值映射<br/>
 * 获取hisInterfaceResult属性的值<br/>
 */
@Data
public abstract class BaseResult implements Serializable {

    /**
     * 成功=1
     * 失败=0
     */
    @JSONField(name = "Result")
    private String result;

    /**
     * 接口测试成功或错误信息
     */
    @JSONField(name = "Msg")
    private String msg;
}