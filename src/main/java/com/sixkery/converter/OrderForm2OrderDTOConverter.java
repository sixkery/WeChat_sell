package com.sixkery.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.sixkery.dataObject.OrderDetail;
import com.sixkery.dataObject.OrderMaster;
import com.sixkery.dto.CartDTO;
import com.sixkery.dto.OrderDTO;
import com.sixkery.enums.ResultEnum;
import com.sixkery.exception.SellException;
import com.sixkery.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sixkery
 * @date 2019/11/16
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName()).setBuyerPhone(orderForm.getPhone())
                .setBuyerAddress(orderForm.getAddress()).setBuyerOpenid(orderForm.getOpenid());
        // 前端传递过来的购物车是 json 格式的,需要转换成 list 格式的
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
            }.getType());
        } catch (JsonSyntaxException e) {
            log.error("【对象转换】错误 String={}", orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);

        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
