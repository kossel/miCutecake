/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.Product;
import java.util.List;

/**
 *
 * @author coslit
 */
public interface ProductService extends GenericService<Product,Integer>{
    public Product getProductWithRecipes (Integer id);
}
