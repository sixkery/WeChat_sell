package com.sixkery.service;

import com.sixkery.dataObject.SellerInfo;
import com.sixkery.repository.SellerInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sixkery
 * @date 2019/11/27
 */
@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo querySellerInfoByOpenId(String openId) {
        return sellerInfoRepository.findByOpenId(openId);
    }
}
