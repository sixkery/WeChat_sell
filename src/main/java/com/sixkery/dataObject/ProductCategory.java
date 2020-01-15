package com.sixkery.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目表
 * @author sixkery
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@DynamicUpdate // 动态更新时间
public class ProductCategory {
    /** 类目 id*/
    @Id
    @GeneratedValue
    private Integer categoryId;
    /** 类目名字*/
    private String categoryName;
    /** 类目 编号*/
    private Integer categoryType;

    private Date createTime;
    private Date updateTime;
}
