package com.sixkery.web;

import com.sixkery.dataObject.OrderDetail;
import com.sixkery.dto.OrderDTO;
import com.sixkery.enums.ResultEnum;
import com.sixkery.exception.SellException;
import com.sixkery.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * 卖家订单
 *
 * @author sixkery
 * @date 2019/11/21
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    OrderMasterService orderMasterService;

    /**
     * 订单列表
     *
     * @param page 第几页从 1 页开始
     * @param size 每页多少条数据
     * @return 订单列表
     */
    @GetMapping("/list")
    public ModelAndView orderList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "size", defaultValue = "8") Integer size,
                                  Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<OrderDTO> orderDTOPage = orderMasterService.findList(request);
        map.put("currentPage", page);
        map.put("orderDTOPage", orderDTOPage);
        return new ModelAndView("order/list", map);

    }

    /**
     * 订单取消
     *
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            OrderDTO cancel = orderMasterService.cancel(orderDTO);
        } catch (SellException e) {
            log.error("【买家端取消订单】 发生异常{}", e);
            map.put("massage", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("massage", ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);

    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            map.put("orderDTO", orderDTO);
            return new ModelAndView("order/detail", map);

        } catch (SellException e) {
            log.error("【买家端订单详情】 发生异常{}", e);
            map.put("massage", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

    }


    /**
     * 完结订单
     * @param orderId
     * @param map
     * @return
     */
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId,
                               Map<String, Object> map) {
        try {
            OrderDTO orderDTO = orderMasterService.findOne(orderId);
            OrderDTO cancel = orderMasterService.finish(orderDTO);
        } catch (SellException e) {
            log.error("【买家端完结订单】 发生异常{}", e);
            map.put("massage", e.getMessage());
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error", map);
        }

        map.put("massage", ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        map.put("url", "/sell/seller/order/list");
        return new ModelAndView("common/success", map);

    }
}
