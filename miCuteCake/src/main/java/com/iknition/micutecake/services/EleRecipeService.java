/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.EleRecipe;
import java.util.List;

/**
 *
 * @author coslit
 */
public interface EleRecipeService extends GenericService<EleRecipe, Integer>{
    public List<EleRecipe> getByRecipe(Integer id);
}
