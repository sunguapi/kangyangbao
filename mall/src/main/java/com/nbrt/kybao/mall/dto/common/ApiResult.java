package com.nbrt.kybao.mall.dto.common;

import com.nbrt.kybao.mall.enums.CodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The type Api result.
 *
 * @param <T> the type parameter
 * @author hjh
 * @date 2022 /5/5 16:02
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class ApiResult<T> extends BaseResult{

    @ApiModelProperty(value = "业务数据")
    private T data;

    /**
     * Instantiates a new Api result.
     */
    public ApiResult() {
    }

    /**
     * Instantiates a new Api result.
     *
     * @param statusCode the status code
     * @param message    the message
     */
    public ApiResult(Integer statusCode, String message) {
        super(statusCode, message);
    }

    /**
     * Instantiates a new Api result.
     *
     * @param statusCode the status code
     * @param message    the message
     * @param data       the data
     */
    public ApiResult(Integer statusCode, String message, T data) {
        super(statusCode, message);
        this.data = data;
    }

    /**
     * 返回成功状态码和信息
     *
     * @param <T> the type parameter
     * @return the api result
     */
    public static <T> ApiResult<T> success() {
        return new ApiResult<T>(CodeEnum.SUCCESS.getCode(),CodeEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 返回成功状态码和信息
     *
     * @param <T> the type parameter
     * @return the api result
     */
    public static <T> ApiResult<T> successWithMessage(String msg) {
        return new ApiResult<T>(CodeEnum.SUCCESS.getCode(),msg, null);
    }

    /**
     * 返回成功信息以及数据
     *
     * @param <T>  the type parameter
     * @param data the data
     * @param msg  the msg
     * @return the api result
     */
    public static <T> ApiResult<T> successWithMessageAndData(String msg,T data ) {
        return new ApiResult<T>(CodeEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回错误状态和信息
     *
     * @param <T>  the type parameter
     * @param code the code
     * @param msg  the msg
     * @return the api result
     */
    public static <T> ApiResult<T> failedWithMessage(Integer code, String msg) {
        return new ApiResult<T>(code, msg, null);
    }

    /**
     * 返回错误状态和信息，以及数据
     *
     * @param <T>  the type parameter
     * @param code the code
     * @param msg  the msg
     * @param data the data
     * @return the api result
     */
    public static <T> ApiResult<T> failedWithMessageAndData(Integer code, String msg, T data) {
        return new ApiResult<T>(code, msg, data);
    }
}
