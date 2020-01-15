package com.sixkery.service;

import com.sixkery.dataObject.SellerInfo;

/**
 * @author sixkery
 */
public interface SellerService {
    /**
     * 根据用户 openid 查询用户
     * @param openId openId
     * @return SellerInfo
     */
    SellerInfo querySellerInfoByOpenId(String openId);
}
