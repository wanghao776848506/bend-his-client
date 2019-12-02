package com.bend.his.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Spring 对于 RuntimeException 异常才会进行事务回滚。
 */
@Getter
@Setter
public class HisException extends RuntimeException {

    public HisException(Integer code) {
        this.code = code;
    }

    public HisException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public HisException(Integer code, String msg, Exception exception) {
        this.code = code;
        this.msg = msg;
        this.exception = exception;
    }

    private Integer code;
    private String msg;
    private Exception exception;
}