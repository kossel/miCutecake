/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.Recipe;
import com.iknition.micutecake.model.daos.RecipeDao;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author coslit
 */
@Service("recipeService")
public class RecipeServiceImpl extends GenericServiceImpl<Recipe, Integer> implements RecipeService {
    
    private RecipeDao recipeDao;

    public RecipeDao getRecipeDao() {
        return recipeDao;
    }

    @Resource
    public void setRecipeDao(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
        this.dao = recipeDao;
    }
    
}
