package com.sixkery.exception;

import com.sixkery.enums.ResultEnum;
import lombok.Getter;

/**
 * @author sixkery
 * @date 2019/11/11
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
