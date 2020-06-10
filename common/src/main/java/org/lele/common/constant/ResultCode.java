package org.lele.common.constant;

/**
 * org.lele.common.constant
 *
 * @author: lele
 * @date: 2020-06-08
 */
public enum ResultCode {
    /**
     * 成功状态码
     */
    SUCCESS(0,"成功"),
    /**
     * 未知错误状态码
     */
    Error(500,"服务器未知错误");


    ResultCode( Integer code,String msg ){
        this.code = code;this.msg=msg;
    }
    private Integer code;
    private String msg;
    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }}
