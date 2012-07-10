/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.Product;
import com.iknition.micutecake.model.daos.IngredientDao;
import com.iknition.micutecake.model.daos.ProductDao;
import javax.annotation.Resource;

/**
 *
 * @author coslit
 */
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
}
