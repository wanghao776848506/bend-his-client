package com.bend.his.common;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * 统一JSON返回类
 *
 * @since 2018/4/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse implements Serializable {

    /**
     * 程序定义状态码
     */
    private int code;
    /**
     * 必要的提示信息
     */
    private String message;
    /**
     * 业务数据
     */
    private Object data;

    /**
     * 对业务数据单独处理
     *
     * @return
     */
    @Override
    public String toString() {
        if (Objects.isNull(this.data)) {
            this.setData(new Object());
        }
        return JSON.toJSONString(this);
    }
}