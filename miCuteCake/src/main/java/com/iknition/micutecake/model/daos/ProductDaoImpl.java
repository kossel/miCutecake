/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.daos;

import com.iknition.micutecake.model.beans.Product;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author coslit
 */
@Repository
public class ProductDaoImpl extends GenericDaoImpl<Product, Integer> implements ProductDao{

    @Override
    public Product getProductWithRecipes(Integer id) {
        Query query = this.getSerssion().createQuery("from Product p left join fetch p.recipes where p.id = ? ");
        query.setParameter(0, id);
        Product product = (Product) query.list().get(0);
        //Hibernate.initialize(product.getRecipes());
        return product;
    }
    
}
