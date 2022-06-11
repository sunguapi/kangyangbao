package com.nbrt.kybao.mall.enums;

import lombok.*;

/**
 * @author hjh
 * @date 2022/5/5 16:20
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CodeEnum {
    // 全局状态码
    SUCCESS(200,"操作成功"),
    NO_PARAM(1001,"参数为空,操作失败"),
    REPEAT_ADD(1003,"重复添加，添加目标已存在"),
    UN_EXIST(1004,"目标不存在"),
    DELETE_ERROR(1005,"删除失败"),
    RECOMMEND_FULL(1006,"推荐商品已满"),
    REPEAT_OPERATE(1007,"重复操作"),
    UPLOAD_ERROR(1008,"上传失败"),
    UNKNOWN(5200,"未知错误");

    int code;
    String message;

}
