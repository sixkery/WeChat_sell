package com.sixkery.handler;

import com.sixkery.VO.ResultVO;
import com.sixkery.exception.SellException;
import com.sixkery.utils.ResultsVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获
 *
 * @author sixkery
 * @date 2019/11/30
 */
@ControllerAdvice
public class SellExceptionHandler {
    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e) {
        return ResultsVOUtil.error(e.getCode(), e.getMessage());
    }
}
