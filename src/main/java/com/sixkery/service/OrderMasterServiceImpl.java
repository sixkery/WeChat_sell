package com.sixkery.service;

import com.sixkery.dataObject.OrderDetail;
import com.sixkery.dataObject.OrderMaster;
import com.sixkery.dataObject.ProductInfo;
import com.sixkery.dto.OrderDTO;
import com.sixkery.enums.ResultEnum;
import com.sixkery.exception.SellException;
import com.sixkery.repository.OrderDetailRepository;
import com.sixkery.repository.OrderMasterRepository;
import com.sixkery.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author sixkery
 * @date 2019/11/11
 */
@Service
public class OrderMasterServiceImpl implements OrderMasterService {
    private BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    // 创建订单
    @Transactional
    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        // 1.查询商品
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 2.计算总价 商品单价 * 商品数量
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            //3.OrderDetail 写入到数据库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            // 把订单对象中的其他对象赋值
            BeanUtils.copyProperties(productInfo, orderDetail);

            OrderDetail orderDetail1 = new OrderDetail();
            orderDetailRepository.save(orderDetail1);
        }

        // 4.写入到数据库(OrderMaster )
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId).setOrderAmount(orderAmount);
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMasterRepository.save(orderMaster);


        // 扣库存
        return null;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderMaster> findList(Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }
}
