package com.sixkery.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * http 请求返回最外层对象
 *
 * @param <T>
 * @author sixkery
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = -2556006241156181255L;
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 具体内容
     */
    private T data;
}
