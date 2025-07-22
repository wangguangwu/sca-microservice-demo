package com.wangguangwu.common.exception;

import lombok.Getter;

/**
 * 业务异常类
 *
 * @author wangguangwu
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     * -- GETTER --
     * 获取错误码
     *
     */
    private Integer code;

    /**
     * 默认构造函数
     */
    public BusinessException() {
        super();
    }

    /**
     * 带消息的构造函数
     *
     * @param message 错误消息
     */
    public BusinessException(String message) {
        super(message);
        this.code = 500;
    }

    /**
     * 带错误码和消息的构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     */
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

}
