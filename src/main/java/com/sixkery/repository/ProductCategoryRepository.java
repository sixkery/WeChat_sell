package com.sixkery.repository;

import com.sixkery.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {
    // 通过多个类别的 id 查询多个类别
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
