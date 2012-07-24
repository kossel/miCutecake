/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.EleRecipe;
import com.iknition.micutecake.model.daos.EleRecipeDao;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author coslit
 */
@Service("eleRecipeService")
public class EleRecipeServiceImpl extends GenericServiceImpl<EleRecipe,Integer> implements EleRecipeService {
    
    private EleRecipeDao eleRecipeDao;

    public EleRecipeDao getEleRecipeDao() {
        return eleRecipeDao;
    }

    @Resource
    public void setEleRecipeDao(EleRecipeDao eleRecipeDao) {
        this.eleRecipeDao = eleRecipeDao;
        this.dao=eleRecipeDao;
    }
    
    
    
}
