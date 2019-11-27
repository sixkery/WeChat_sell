package com.sixkery.repository;

import com.sixkery.dataObject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;
    private final static String OPENID = "110110";

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457").setBuyerName("sixkery").setBuyerOpenid("110110").setBuyerAddress("144号").setBuyerPhone("1234567")
                .setOrderAmount(new BigDecimal(3.6));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0, 1);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
        long totalElements = result.getTotalElements();
        System.out.println("totalElements = " + totalElements);
        Assert.assertNotEquals(0, result.getTotalElements());
    }
}
