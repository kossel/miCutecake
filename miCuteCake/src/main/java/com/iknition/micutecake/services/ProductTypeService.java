/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.ProductType;
import java.util.List;

/**
 *
 * @author Yichao
 */
public interface ProductTypeService {
    public List getAll();
    public void save(ProductType productType);
    public ProductType getById(Integer id);
}
