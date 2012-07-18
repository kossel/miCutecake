/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.Product;
import com.iknition.micutecake.model.daos.IngredientDao;
import com.iknition.micutecake.model.daos.ProductDao;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author coslit
 */
@Service("productService")
public class ProductServiceImpl extends GenericServiceImpl<Product, Integer> implements ProductService{
    private ProductDao productDao;

    public ProductDao getProductDao() {
        return productDao;
    }

    @Resource
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
        this.dao = productDao;
    }
    
    @Transactional(readOnly=true)
    @Override
    public Product getProductWithRecipes (Integer id){
        Product p = productDao.getProductWithRecipes(id);
        return p;
    }
}
