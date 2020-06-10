package org.lele.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.lele.common.constant.ResultCode;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * org.lele.common.dto
 *
 * @author: lele
 * @date: 2020-06-08
 */
public class CommonResult<T> implements Serializable {

    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 无限制，返回
     *   没有状态码的新建状态码。
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> CommonResult<T> result(ResultCode resultCode) {
        CommonResult<T> result = new CommonResult<>();
        result.code = resultCode.getCode();
        result.message = resultCode.getMsg();
        return result;
    }

    /**
     * 将传入的 result 对象，转换成另外一个泛型结果的对象
     *
     * @param result 传入的 result 对象
     * @param <T> 返回的泛型
     * @return 新的 CommonResult 对象
     */
    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMessage());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!ResultCode.SUCCESS.getCode().equals(code), "code 必须是错误的！");
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.message = message;
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = ResultCode.SUCCESS.getCode();
        result.data = data;
        result.message = ResultCode.SUCCESS.getMsg();
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JsonIgnore
    public boolean isSuccess() {
        return ResultCode.SUCCESS.getCode().equals(code);
    }

    @JsonIgnore
    public boolean isError() {
        return !isSuccess();
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
