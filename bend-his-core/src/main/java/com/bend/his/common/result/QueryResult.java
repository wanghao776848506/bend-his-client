package com.bend.his.common.result;

import lombok.Data;

@Data
public class QueryResult<T> extends BaseResult {

    private T data;

}
