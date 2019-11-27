package com.sixkery.web;

import com.sixkery.VO.ResultVO;
import com.sixkery.converter.OrderForm2OrderDTOConverter;
import com.sixkery.dto.OrderDTO;
import com.sixkery.enums.ResultEnum;
import com.sixkery.exception.SellException;
import com.sixkery.form.OrderForm;
import com.sixkery.service.BuyerService;
import com.sixkery.service.OrderMasterService;
import com.sixkery.utils.ResultsVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sixkery
 * @date 2019/11/17
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderMasterService orderMasterService;
    @Autowired
    private BuyerService buyerService;

    /**
     * 创建订单
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确，orderForm={}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        // 判断购物车是否为空
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderMasterService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultsVOUtil.success(map);
    }

    /**
     * 订单列表
     */
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "5") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid 为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request = new PageRequest(page, size);
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(openid, request);
        return ResultsVOUtil.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     */
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultsVOUtil.success(orderDTO);

    }

    /**
     * 取消订单
     */
    @PostMapping("/detail")
    public ResultVO<OrderDTO> cancel(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        buyerService.cancelOrder(openid, orderId);
        return ResultsVOUtil.success();

    }

}
