package com.sixkery.exception;

import com.sixkery.enums.ResultEnum;

/**
 * @author sixkery
 * @date 2019/11/11
 */
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();

    }
}
