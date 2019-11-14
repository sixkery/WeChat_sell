package com.sixkery.repository;

import com.sixkery.dataObject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void save() {
        OrderDetail s = new OrderDetail();
        s.setDetailId("123456788").setOrderId("1111111").setProductIcon("http://xxx.jpg").setProductName("皮蛋瘦肉粥")
                .setProductPrice(new BigDecimal(3.3)).setProductQuantity(3).setProductId("111112");

        OrderDetail save = repository.save(s);
        Assert.assertNotNull(save);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = repository.findByOrderId("1111111");
        Assert.assertNotEquals(0, result.size());
    }
}