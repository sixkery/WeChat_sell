package com.sixkery.web;

import com.google.protobuf.RpcUtil;
import com.sixkery.dataObject.ProductCategory;
import com.sixkery.exception.SellException;
import com.sixkery.form.CategoryForm;
import com.sixkery.service.CategoryService;
import com.sixkery.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 卖家端类目管理
 *
 * @author sixkery
 * @date 2019/11/27
 */
@RestController
@Slf4j
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("/category/list", map);
    }

    /**
     * 展示修改页面
     *
     * @param categoryId 类别 id
     * @param map
     * @return
     */
    @GetMapping("index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId != null) {
            ProductCategory category = categoryService.findOne(categoryId);
            map.put("category", category);
        }
        return new ModelAndView("/category/index", map);
    }

    /**
     * 保存/修改
     *
     * @param form
     * @param bindingResult
     * @param map
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm form, BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) {
            map.put("message", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("/common/error", map);
        }
        ProductCategory productCategory = new ProductCategory();

        try {
            if (!StringUtils.isEmpty(form.getCategoryId())) {
                // 修改
                productCategory = categoryService.findOne(form.getCategoryId());
                BeanUtils.copyProperties(form, productCategory);
                productCategory.setUpdateTime(new Date());
            } else {
                // 新增
                BeanUtils.copyProperties(form, productCategory);
                productCategory.setCreateTime(new Date()).setUpdateTime(new Date());
            }
            categoryService.saveCategory(productCategory);
        } catch (SellException e) {
            map.put("massage", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("/common/error", map);
        }

        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("/common/success", map);

    }
}
