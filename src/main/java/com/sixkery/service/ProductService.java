package com.sixkery.service;

import com.sixkery.dataObject.ProductCategory;
import com.sixkery.dataObject.ProductInfo;
import com.sixkery.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author sixkery
 */
public interface ProductService {
    // 通过商品的 id 查询商品
    ProductInfo findOne(String productId);

    // 查询上架的商品列表
    List<ProductInfo> findUpAll();

    // 分页查询
    Page<ProductInfo> findAll(Pageable pageable);

    List<ProductInfo> findByProductStatus(Integer productStatus);

    ProductInfo saveProduct(ProductInfo productInfo);

    // 加库存
    void increaseStock(List<CartDTO> cartDTOList);

    // 减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    /** 上架 */
    ProductInfo onSale(String productId);

    /** 下架 */
    ProductInfo offSale(String productId);


}
