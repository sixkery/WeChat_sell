package com.sixkery.repository;

import com.sixkery.dataObject.ProductCategory;
import com.sixkery.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author sixkery
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {
    /**
     * 查询上架的商品
     *
     * @param productStatus 商品的状态
     * @return 商品列表
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
