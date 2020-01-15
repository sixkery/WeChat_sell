package com.sixkery.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sixkery
 * @date 2019/11/28
 */
@Component
@Data
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlConfig {

    /**
     * 微信公众平台授权 url
     */
    private String wechatMpAuthorize;
    /**
     * 微信开放平台授权 url
     */
    private String wechatOpenAuthorize;
    /**
     * 点餐系统
     */
    private String sell;

}
