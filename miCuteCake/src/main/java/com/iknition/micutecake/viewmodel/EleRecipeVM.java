/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.*;
import com.iknition.micutecake.services.EleRecipeService;
import com.iknition.micutecake.services.EventAddrService;
import com.iknition.micutecake.services.IngredientService;
import com.iknition.micutecake.services.ProductService;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
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
    private ListModelList<Ingredient> ingredientsList;
    
    private EleRecipe selected;
    private Ingredient selectedIngredient;
    private EleRecipe newEleRecipe;
    
    
    @WireVariable
    private EleRecipeService eleRecipeService;
    @WireVariable
    private IngredientService ingredientService;

    @GlobalCommand @NotifyChange("ingredients")
    public void showDetailRecipe(@BindingParam("item")Recipe item){
            EleRecipeService s = this.getEleRecipeService();
            List a = s.getByRecipe(item.getId());
            ingredients = new ListModelList<EleRecipe>(a);//init the list
            this.newEleRecipe=new EleRecipe();
            this.newEleRecipe.setRecipe(item);
    }
    
    @Command
    public void addIngredient(){
        this.getNewEleRecipe().setUnit(this.getNewEleRecipe().getIngredient().getUnit());
        this.getEleRecipeService().saveWithoutDuplicate(this.getNewEleRecipe()); 
        this.getIngredients().add(this.getNewEleRecipe());
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

    public IngredientService getIngredientService() {
        return ingredientService;
    }

    public void setIngredientService(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    public ListModelList<Ingredient> getIngredientsList() {
        if(this.ingredientsList==null){
            List a =this.getIngredientService().getAll();
            this.ingredientsList = new ListModelList<Ingredient>(a);
        }
        return ingredientsList;
    }

    public void setIngredientsList(ListModelList<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public Ingredient getSelectedIngredient() {
        return selectedIngredient;
    }

    public void setSelectedIngredient(Ingredient selectedIngredient) {
        this.selectedIngredient = selectedIngredient;
    }

    public EleRecipe getNewEleRecipe() {
        return newEleRecipe;
    }

    public void setNewEleRecipe(EleRecipe newEleRecipe) {
        System.out.println("selected - newEleRecipe");
        this.newEleRecipe = newEleRecipe;
    }
    
    
    
    
}
