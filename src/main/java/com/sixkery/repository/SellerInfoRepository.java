package com.sixkery.repository;

import com.sixkery.dataObject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author sixkery
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {
    /**
     * 根据 openid 查询单个卖家
     * @param openId openid
     * @return
     */
    SellerInfo findByOpenId(String openId);
}
