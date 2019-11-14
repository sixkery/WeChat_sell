package com.sixkery.VO;

import lombok.Data;

/**
 * http 请求返回最外层对象
 *
 * @param <T>
 */
@Data
public class ResultVO<T> {
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
