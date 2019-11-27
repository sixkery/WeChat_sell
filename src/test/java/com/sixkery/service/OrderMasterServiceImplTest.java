package com.sixkery.service;

import com.sixkery.dataObject.OrderDetail;
import com.sixkery.dto.CartDTO;
import com.sixkery.dto.OrderDTO;
import com.sixkery.enums.OrderStatusEnum;
import com.sixkery.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterService orderMasterService;

    private final String BUYER_OPENID = "1101110";

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("回龙观").setBuyerName("sixkery").setBuyerOpenid(BUYER_OPENID).setBuyerPhone("1234567")
                .setOrderAmount(new BigDecimal(3));

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123567").setProductQuantity(1);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457").setProductQuantity(1);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);


        OrderDTO result = orderMasterService.create(orderDTO);
        log.info("【创建订单】result={}", result);
        Assert.assertNotNull(result);

    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderMasterService.findOne("1573747420184831971");
        log.info("【查询单个订单】result={}", orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest(0, 2);
        Page<OrderDTO> list = orderMasterService.findList(BUYER_OPENID, request);
        for (OrderDTO orderDTO : list) {
            System.out.println("orderDTO = " + orderDTO);
        }

    }


    @Test
    public void finish() {
        OrderDTO orderDTO = orderMasterService.findOne("1573747420184831971");
        OrderDTO result = orderMasterService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(), result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderMasterService.findOne("1573747420184831971");
        OrderDTO result = orderMasterService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderMasterService.findOne("1573747420184831971");
        OrderDTO result = orderMasterService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());

    }

    @Test
    public void findAllByPage() {
        PageRequest request = new PageRequest(0, 1);
        Page<OrderDTO> list = orderMasterService.findList(request);
        Assert.assertTrue("查询所有的订单列表", list.getTotalElements() > 0);
    }
}