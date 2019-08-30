package com.bend.his.common.request;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "共用的查询参数")
public class QueryRequest extends BaseRequest {

    @JSONField(name = "authCode")
    @ApiModelProperty(name = "验证码",notes = "验证码")
    private String authVerificationCode;

}
