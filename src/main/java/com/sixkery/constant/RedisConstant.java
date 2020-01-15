package com.sixkery.constant;

/**
 * redis 常量
 *
 * @author sixkery
 * @date 2019/11/28
 */
public interface RedisConstant {
    /**
     * 前缀
     */
    String TOKEN_PREFIX = "token_%s";
    /**
     * 过期时间 2 小时
     */
    Integer EXPIRE = 7200;
}
