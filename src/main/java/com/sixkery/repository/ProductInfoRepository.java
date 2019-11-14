package com.sixkery.repository;

import com.sixkery.dataObject.ProductCategory;
import com.sixkery.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    // 查询上架的商品
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
