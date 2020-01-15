package com.sixkery.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author sixkery
 * @date 2019/11/28
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    /**
     * g公众平台 id
     */
    private String mpAppId;
    /**
     * 公众平台密钥
     */
    private String mpAppSecret;
    /**
     * 开放平台 id
     */
    private String openAppId;
    /**
     * 开放平台 密钥
     */
    private String openAppSecret;
    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;
    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 微信支付异步通知地址
     */
    private String notifyUrl;

    /**
     * 微信模版id
     */
    private Map<String, String> templateId;

}
