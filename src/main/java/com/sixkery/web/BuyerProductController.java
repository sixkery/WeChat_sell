package com.sixkery.web;

import com.sixkery.VO.ProductInfoVO;
import com.sixkery.VO.ProductVO;
import com.sixkery.VO.ResultVO;
import com.sixkery.dataObject.ProductCategory;
import com.sixkery.dataObject.ProductInfo;
import com.sixkery.service.CategoryService;
import com.sixkery.service.ProductService;
import com.sixkery.utils.ResultsVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sixkery
 */
@RestController
@RequestMapping(path = "/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping(path = "/list")
    public ResultVO list() {
        // 1. 查询所有上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        // 2. 查询需要的类目(一次性全部查询)
        // lambda
        List<Integer> categoryTypeList = productInfoList.stream().map(ProductInfo::getCategoryType).collect(Collectors.toList());

        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        // 3. 拼装数据

        List<ProductVO> productVOList = new ArrayList<>();

        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }

        return ResultsVOUtil.success(productVOList);


    }

}
