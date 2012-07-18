/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.daos;

import com.iknition.micutecake.model.beans.Product;
import java.util.List;

/**
 *
 * @author coslit
 */
public interface ProductDao extends GenericDao<Product, Integer> {
    
    public Product getProductWithRecipes(Integer id);
    
}
