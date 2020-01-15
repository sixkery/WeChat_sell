package com.sixkery.aspect;

import com.sixkery.constant.CookieConstant;
import com.sixkery.constant.RedisConstant;
import com.sixkery.exception.SellerAuthorizeException;
import com.sixkery.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 卖家授权 aop 身份验证
 *
 * @author sixkery
 * @date 2019/11/29
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 切去掉 SellerUserController 以外的方法
     */
    @Pointcut("execution(public * com.sixkery.web.Seller*.*(..))" +
            "&& !execution(public * com.sixkery.web.SellerUserController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        // 查询 cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登陆校验】Cookie 中查询不到 token");
            throw new SellerAuthorizeException();
        }
        //去 redis 里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】Redis中查不到token");
            throw new SellerAuthorizeException();
        }
    }
}
