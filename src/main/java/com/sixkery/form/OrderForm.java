package com.sixkery.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author sixkery
 * @date 2019/11/17
 */
@Data
public class OrderForm {
    /**
     * 买家名字
     */
    @NotEmpty(message = "姓名必填")
    private String name;
    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号不能为空")
    private String phone;
    /**
     * 买家地址
     */
    @NotEmpty(message = "地址不能为空")
    private String address;
    /**
     * 买家微信 openid
     */
    @NotEmpty(message = "买家微信 openID 不能为空")
    private String openid;
    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;

}
