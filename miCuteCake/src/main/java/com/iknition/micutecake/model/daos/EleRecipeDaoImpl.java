/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.daos;

import com.iknition.micutecake.model.beans.EleRecipe;
import com.iknition.micutecake.model.beans.Product;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author coslit
 */
@Repository
public class EleRecipeDaoImpl extends GenericDaoImpl<EleRecipe,Integer> implements EleRecipeDao{
 
    @Override
    public List<EleRecipe> getIngredientsByRecipe(Integer id){
        Query query = this.getSerssion().createQuery("from EleRecipe e left join fetch e.recipe left join fetch e.ingredient where e.recipe.id = ? ");
        query.setParameter(0, id);
        List<EleRecipe> eleRecipes = (List<EleRecipe>)query.list();
        //Hibernate.initialize(product.getRecipes());
        return eleRecipes;
    }

    @Override
    public List<EleRecipe> getByIngredient(Integer id) {
        Query query = this.getSerssion().createQuery("from EleRecipe e where e.ingredient.id = ? ");
        query.setParameter(0, id);
        List<EleRecipe> eleRecipes = (List<EleRecipe>)query.list();
        return eleRecipes;
    }
    
    
    
}
