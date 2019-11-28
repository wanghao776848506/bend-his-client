package com.bend.his.common;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * lombok的getter和@JSONField 无法进行值映射<br/>
 * 获取hisInterfaceResult属性的值<br/>
 */
@Data
@ApiModel(value = "HISResult - 接口响应基础数据", description = "BaseResult")
public class HISResult implements Serializable {

    /**
     * 成功=1
     * 失败=0
     */
    @ApiModelProperty(notes = "接口响应业务码[成功=1,失败=0]")
    @JSONField(name = "Result")
    private String result;

    /**
     * 接口测试成功或错误信息
     */
    @ApiModelProperty(notes = "接口响应成功或错误信息")
    @JSONField(name = "Msg")
    private String msg;

    @ApiModelProperty(notes = "接口响应数据条数")
    @JSONField(name = "Total")
    private String total;


}