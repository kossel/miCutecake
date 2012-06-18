/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.ProductType;
import com.iknition.micutecake.model.daos.ProductTypeDao;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Yichao
 */
@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    
    @Resource
    ProductTypeDao productDao;
    
    @Override
    @Transactional(readOnly=true)
    public List<ProductType> getAll() {
        List result = productDao.getList();
        return result;
    }

    public ProductTypeDao getProductDao() {
        return productDao;
    }

    public void setProductDao(ProductTypeDao ptDao) {
        this.productDao = ptDao;
    }

    @Override
    @Transactional
    public void save(ProductType productType) {
        this.productDao.saveOrUpdate(productType);
    }

    @Override
    @Transactional(readOnly=true)
    public ProductType getById(Integer id) {
        return this.productDao.load(id);
    }
    
    @Override
    @Transactional
    public void delete(Integer id){
        this.productDao.deleteById(id);
    }
    
    
    
}
