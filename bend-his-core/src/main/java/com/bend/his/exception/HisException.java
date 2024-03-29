package com.bend.his.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HisException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * 自定义错误讯息.
   */
  private String customErrorMsg;
  /**
   * 返回状态码.
   */
  private String returnCode;
  /**
   * 返回信息.
   */
  private String returnMsg;

  /**
   * 业务结果.
   */
  private String resultCode;

  /**
   * 错误代码.
   */
  private String errCode;

  /**
   * 错误代码描述.
   */
  private String errCodeDes;

  /**
   * 微信支付返回的结果xml字符串.
   */
  private String xmlString;

  /**
   * Instantiates a new Wx pay exception.
   *
   * @param customErrorMsg the custom error msg
   */
  public HisException(String customErrorMsg) {
    super(customErrorMsg);
    this.customErrorMsg = customErrorMsg;
  }

  /**
   * Instantiates a new Wx pay exception.
   *
   * @param customErrorMsg the custom error msg
   * @param tr             the tr
   */
  public HisException(String customErrorMsg, Throwable tr) {
    super(customErrorMsg, tr);
    this.customErrorMsg = customErrorMsg;
  }

  private HisException(Builder builder) {
    returnCode = builder.returnCode;
    returnMsg = builder.returnMsg;
    resultCode = builder.resultCode;
    errCode = builder.errCode;
    errCodeDes = builder.errCodeDes;
    xmlString = builder.xmlString;
  }

  /**
   * 通过BaseResult生成异常对象.
   *
   * @param baseResult the pay base result
   * @return the wx pay exception
   */
//  public static HisException from(BaseResult baseResult) {
//    return HisException.newBuilder()
//      .xmlString(baseResult.getXmlString())
//      .returnMsg(baseResult.getReturnMsg())
//      .returnCode(baseResult.getReturnCode())
//      .resultCode(baseResult.getResultCode())
//      .errCode(baseResult.getErrCode())
//      .errCodeDes(baseResult.getErrCodeDes())
//      .build();
//  }

  /**
   * New builder builder.
   *
   * @return the builder
   */
  public static Builder newBuilder() {
    return new Builder();
  }

  /**
   * The type Builder.
   */
  public static final class Builder {
    private String returnCode;
    private String returnMsg;
    private String resultCode;
    private String errCode;
    private String errCodeDes;
    private String xmlString;

    private Builder() {
    }

    /**
     * Return code builder.
     *
     * @param returnCode the return code
     * @return the builder
     */
    public Builder returnCode(String returnCode) {
      this.returnCode = returnCode;
      return this;
    }

    /**
     * Return msg builder.
     *
     * @param returnMsg the return msg
     * @return the builder
     */
    public Builder returnMsg(String returnMsg) {
      this.returnMsg = returnMsg;
      return this;
    }

    /**
     * Result code builder.
     *
     * @param resultCode the result code
     * @return the builder
     */
    public Builder resultCode(String resultCode) {
      this.resultCode = resultCode;
      return this;
    }

    /**
     * Err code builder.
     *
     * @param errCode the err code
     * @return the builder
     */
    public Builder errCode(String errCode) {
      this.errCode = errCode;
      return this;
    }

    /**
     * Err code des builder.
     *
     * @param errCodeDes the err code des
     * @return the builder
     */
    public Builder errCodeDes(String errCodeDes) {
      this.errCodeDes = errCodeDes;
      return this;
    }

    /**
     * Xml string builder.
     *
     * @param xmlString the xml string
     * @return the builder
     */
    public Builder xmlString(String xmlString) {
      this.xmlString = xmlString;
      return this;
    }

    /**
     * Build wx pay exception.
     *
     * @return the wx pay exception
     */
    public HisException build() {
      return new HisException(this);
    }
  }
}