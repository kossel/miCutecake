/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.Ingredient;
import com.iknition.micutecake.model.daos.IngredientDao;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author coslit
 */
@Service("ingredientService")
public class IngredientServiceImpl extends GenericServiceImpl<Ingredient, Integer> implements IngredientService{
    
     
    private IngredientDao ingredientDao;

    public IngredientDao getClientDao() {
        return ingredientDao;
    }

    @Resource
    public void setIngredientDao(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
        this.dao = ingredientDao;
    }
    
    
}
