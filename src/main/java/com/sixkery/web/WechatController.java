package com.sixkery.web;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sixkery
 * @date 2019/11/19
 */
@RestController
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
    @GetMapping("/authorize")
    public void authorize(@RequestParam("returnUrl") String returnUrl) {
        WxMpService wxMpService = new WxMpServiceImpl();
        //1.配置
        //2.调用方法


    }
}
