package com.sixkery.repository;

import com.sixkery.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Autowired
    private ProductInfoRepository infoRepository;

    @Test
    public void findOneTest() {
        ProductCategory one = repository.findOne(1);
        System.out.println("one = " + one);


    }

    @Transactional
    @Test
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱").setCategoryType(7);
        ProductCategory category = repository.save(productCategory);
        Assert.assertNotNull(category);
    }
}