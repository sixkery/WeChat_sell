package com.sixkery.web;

import com.sixkery.config.ProjectUrlConfig;
import com.sixkery.constant.CookieConstant;
import com.sixkery.constant.RedisConstant;
import com.sixkery.dataObject.SellerInfo;
import com.sixkery.enums.ResultEnum;
import com.sixkery.service.SellerService;
import com.sixkery.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author sixkery
 * @date 2019/11/28
 */
@Controller
@Slf4j
@RequestMapping("/seller")
public class SellerUserController {
    @Autowired
    private SellerService sellerService;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openId") String openId, HttpServletResponse response,
                              Map<String, Object> map) {
        // openId 跟数据库中的 openId 比对

        SellerInfo sellerInfo = sellerService.querySellerInfoByOpenId(openId);
        if (sellerInfo == null) {
            map.put("message", ResultEnum.LOGIN_FAIL.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("/common/error", map);
        }
        // 设置 token 到 redis 中
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        // String.format(RedisConstant.TOKEN_PREFIX,token) 格式化，以token_开头:key
        // openId:value
        // expire:过期时间
        // 单位 TimeUnit.SECONDS
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openId, expire, TimeUnit.SECONDS);
        // 设置 token 到 cookies 中
        CookieUtil.set(response, CookieConstant.TOKEN, token, expire);


        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");

    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> map) {
        // 获取cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            // 清除 redis 中 cookie
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            // 清除 request 中的cookie
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }
        map.put("message", ResultEnum.LOGOUT_SUCCESS);
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);

    }
}
