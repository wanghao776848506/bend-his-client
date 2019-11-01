package com.bend.his.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "QueryResult - 接口响应扩展数据", description = "QueryResult")
public class QueryResult<T> extends BaseResult {

    @ApiModelProperty(notes = "接口响应数据")
    private T data;

}
