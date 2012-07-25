/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.daos;

import com.iknition.micutecake.model.beans.EleRecipe;
import java.util.List;

/**
 *
 * @author coslit
 */
public interface EleRecipeDao extends GenericDao<EleRecipe,Integer> {
    
    public List<EleRecipe> getIngredientsByRecipe(Integer id);
    
}
