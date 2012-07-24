/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.EleRecipe;
import com.iknition.micutecake.model.beans.EventAddress;
import com.iknition.micutecake.model.beans.Product;
import com.iknition.micutecake.services.EleRecipeService;
import com.iknition.micutecake.services.EventAddrService;
import com.iknition.micutecake.services.ProductService;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author coslit
 */
public class EleRecipeVM {
    private ListModelList<EleRecipe> ingredients;
    private EleRecipe selected;
    
    @WireVariable
    private EleRecipeService eleRecipeService;

    @GlobalCommand @NotifyChange({"selected","ingredients"})
    public void showDetailRecipe(@BindingParam("id")Integer id){
            EleRecipeService s = this.getEleRecipeService();
            List a = s.getAll();
            ingredients = new ListModelList<EleRecipe>(a);//init the list
    }
    
    public ListModelList<EleRecipe> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ListModelList<EleRecipe> ingredients) {
        this.ingredients = ingredients;
    }

    public EleRecipe getSelected() {
        return selected;
    }

    public void setSelected(EleRecipe selected) {
        this.selected = selected;
    }

    public EleRecipeService getEleRecipeService() {
        return eleRecipeService;
    }

    public void setEleRecipeService(EleRecipeService eleRecipeService) {
        this.eleRecipeService = eleRecipeService;
    }
    
    
    
}
