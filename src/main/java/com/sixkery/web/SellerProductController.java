package com.sixkery.web;

import com.sixkery.dataObject.ProductCategory;
import com.sixkery.dataObject.ProductInfo;
import com.sixkery.exception.SellException;
import com.sixkery.form.ProductForm;
import com.sixkery.service.CategoryService;
import com.sixkery.service.ProductService;
import com.sixkery.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 卖家端商品
 *
 * @author sixkery
 * @date 2019/11/26
 */
@Slf4j
@RestController
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView productList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "size", defaultValue = "8") Integer size,
                                    Map<String, Object> map) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(request);
        map.put("currentPage", page);
        map.put("productInfoPage", productInfoPage);
        return new ModelAndView("product/list", map);

    }

    @GetMapping("/on_sale")
    public ModelAndView productOnSale(@RequestParam("productId") String productId,
                                      Map<String, Object> map) {
        try {
            productService.onSale(productId);
        } catch (SellException e) {
            map.put("massage", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);

    }

    @GetMapping("/off_sale")
    public ModelAndView productOffSale(@RequestParam("productId") String productId,
                                       Map<String, Object> map) {
        try {
            productService.offSale(productId);
        } catch (SellException e) {
            map.put("massage", e.getMessage());
            map.put("url", "/sell/seller/product/list");
            return new ModelAndView("/common/error", map);
        }

        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }

    /**
     * index 页面
     *
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        // 查询所有的类目
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("/product/index", map);

    }

    /**
     * 新增和修改
     *
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm form, BindingResult bindingResult,
                             Map<String, Object> map) {
        log.info("【新增和修改】form={}", form);
        if (bindingResult.hasErrors()) {
            map.put("massage", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("/common/error", map);
        }
        try {
            ProductInfo productInfo = new ProductInfo();
            // 如果 商品ID 不为空，则为修改
            if (!StringUtils.isEmpty(form.getProductId())) {
                productInfo = productService.findOne(form.getProductId());
                productInfo.setUpdateTime(new Date());
            } else {
                form.setProductId(KeyUtil.genUniqueKey());
                productInfo.setCreateTime(new Date());
            }
            BeanUtils.copyProperties(form, productInfo);
            productService.saveProduct(productInfo);
        } catch (SellException e) {
            map.put("massage", e.getMessage());
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("/common/error", map);
        }
        map.put("url", "/sell/seller/product/list");
        return new ModelAndView("/common/success", map);
    }
}
