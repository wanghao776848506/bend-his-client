package com.bend.his.common;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;

@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "共用的查询参数")
public class CommonPojo<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 入口参数-交易编号
     */
    @XmlElement(name = "TradeCode")
    @ApiModelProperty(notes = "接口交易编号")
    @JSONField(name = "交易编号")
    private String tradeCode;

    /**
     * 入口参数-交易参数【JSON格式】
     */
    @ApiModelProperty(notes = "交易参数【JSON格式】", hidden = true)
    @XmlElement(name = "InputParameter")
    private String inputParameter;

    /**
     * 请求业务参数对象
     */
    @ApiModelProperty(notes = "业务参数对象")
    private T data;



}
