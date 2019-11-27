package com.sixkery.dataObject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sixkery.enums.ProductStatusEnum;
import com.sixkery.form.ProductForm;
import com.sixkery.utils.EnumUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sixkery
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@DynamicUpdate
public class ProductInfo {
    @Id
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    /**
     * 库存
     */
    private Integer productStock;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 小图
     */
    private String productIcon;
    /**
     * 商品状态 0 正常，1 下架
     */
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    /**
     * 类目编号 商品和类目的关系
     */
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
