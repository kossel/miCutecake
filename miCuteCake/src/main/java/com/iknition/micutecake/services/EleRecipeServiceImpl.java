/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.EleRecipe;
import com.iknition.micutecake.model.daos.EleRecipeDao;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    

    @Override
    @Transactional(readOnly=true)
    public List<EleRecipe> getByRecipe(Integer id) {
        List<EleRecipe> list = eleRecipeDao.getIngredientsByRecipe(id);
        return list;
    }
    
}
