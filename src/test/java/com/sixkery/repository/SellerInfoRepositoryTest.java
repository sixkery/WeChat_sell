package com.sixkery.repository;

import com.sixkery.dataObject.SellerInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SellerInfoRepositoryTest {
    @Autowired
    SellerInfoRepository repository;

    @Test
    public void save() {
        SellerInfo sellerInfo = repository.save(new SellerInfo("1", "admin", "admin", "123"));
        Assert.assertNotNull(sellerInfo);
    }

    @Test
    public void findOne() {
        SellerInfo sellerInfo = repository.findByOpenId("123");
        log.info("【查询单条数据】sellerInfo={}", sellerInfo);
        Assert.assertNotNull(sellerInfo);
    }
}