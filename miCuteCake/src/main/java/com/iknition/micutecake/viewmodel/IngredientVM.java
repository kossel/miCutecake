/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.Ingredient;
import com.iknition.micutecake.services.IngredientService;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

/**
 *
 * @author coslit
 */

public class IngredientVM {
    private ListModelList<Ingredient> ingredients;
    private Ingredient selected;
    private String deleteMessage;
    
    @Wire("#ingredientModal")
    private Window ingredientModal;
    
    @WireVariable
    private IngredientService ingredientService;

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
    
    public ListModelList<Ingredient> getIngredients() {
        if (ingredients == null) {
            IngredientService s = this.getIngredientService();
             List a = s.getAll();
            ingredients = new ListModelList<Ingredient>(a);//init the list
        }
        return ingredients;
    }

    
    @NotifyChange("selected")
    public void setSelected(Ingredient selected) {
        this.selected = selected;
        this.openModal();
    }

    public Ingredient getSelected() {
        return selected;
    }
    
    
    
    @Command
    public void openModal(){
        this.ingredientModal.setVisible(true);
    }
    
    @Command 
    @NotifyChange({"selected","ingredients"})
    public void newIngredient(){
        Ingredient ingredient = new Ingredient();
     //   this.getConceptTypes().add(conceptType);
        selected = ingredient;//select the new one
        this.openModal();
        
    }
    
    @Command @NotifyChange("selected")
    public void saveIngredient(){
        getIngredientService().saveOrUpdate(selected);
        int idx = this.getIngredients().indexOf(selected);
        if(idx>=0){
            this.getIngredients().remove(selected);
            this.getIngredients().add(idx, selected);
        }else{
            this.getIngredients().add(selected);
        }
        this.ingredientModal.setVisible(false);
    }
    
    @Command @NotifyChange({"selected","ingredients", "deleteMessage"})
    public void deleteIngredient(){
        this.getIngredientService().delete(selected.getId());//delete selected
        this.getIngredients().remove(selected);
        selected = null; //clean the selected
        this.deleteMessage=null;
    }

    @Command @NotifyChange({"selected","ingredients", "deleteMessage"})
    public void confirmDelete2(@BindingParam("item") Ingredient item ) {
         this.selected=item;
        deleteMessage = "Do you want to delete "+selected.getName()+" ?";
       
    }
    
   
    @Command
    public void cerrarModal(BindContext ctx){
            ctx.getTriggerEvent().stopPropagation();
          this.ingredientModal.setVisible(false); 
    }
    
    @Command @NotifyChange("deleteMessage")
    public void confirmDelete(){
        //set the message to show to user
        deleteMessage = "Do you want to delete "+selected.getName()+" ?";
    }
    
    @Command @NotifyChange("deleteMessage")
    public void cancelDelete(){
        //clear the message
        deleteMessage = null;
    }
 
  

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(String deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public IngredientService getIngredientService() {
        return ingredientService;
    }

    public void setIngredientService(IngredientService IngredientService) {
        this.ingredientService = IngredientService;
    }

    
}
