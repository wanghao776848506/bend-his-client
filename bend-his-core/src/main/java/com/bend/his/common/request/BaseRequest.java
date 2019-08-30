package com.bend.his.common.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

/**
 * 请求对象共用的参数存放类
 */
@Data
@ApiModel(description = "请求对象共用的参数存放类")
public abstract class BaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 入口参数-交易编号
     */
    @XmlElement(name = "TradeCode")
    @ApiModelProperty(notes = "交易编号")
    @JSONField(name = "交易编号", ordinal = 0)
    protected String tradeCode;

    /**
     * 入口参数-交易参数【JSON格式】
     */
    @XmlElement(name = "InputParameter")
    @ApiModelProperty(notes = "交易参数【JSON格式】", hidden = true)
    protected String inputParameter;


}
