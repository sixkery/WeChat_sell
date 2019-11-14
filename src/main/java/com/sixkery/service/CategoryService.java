package com.sixkery.service;

import com.sixkery.dataObject.ProductCategory;

import java.util.List;

public interface CategoryService {
    // 通过类别的 id 查询类别
    ProductCategory findOne(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory saveCategory(ProductCategory productCategory);

}
