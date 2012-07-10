/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.ProductType;
import com.iknition.micutecake.services.ProductTypeService;
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

public class ProductTypeVM {
    private ListModelList<ProductType> productTypes;
    private ProductType selected;
    private String deleteMessage;
    
    @Wire("#productTypeModal")
    private Window productTypeModal;
    
    @WireVariable
    private ProductTypeService productTypeService;

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        System.out.println("--composed-----");
    }
    
    public ListModelList<ProductType> getProductTypes() {
        if (productTypes == null) {
            ProductTypeService s = this.getProductTypeService();
             List a = s.getAll();
            productTypes = new ListModelList<ProductType>(a);//init the list
        }
        return productTypes;
    }

    
    @NotifyChange("selected")
    public void setSelected(ProductType selected) {
        this.selected = selected;
        this.openModal();
    }

    public ProductType getSelected() {
        return selected;
    }
    
    
    
    @Command
    public void openModal(){
        this.productTypeModal.setVisible(true);
    }
    
    @Command 
    @NotifyChange({"selected","ingredients"})
    public void newProductType(){
        ProductType ingredient = new ProductType();
     //   this.getConceptTypes().add(conceptType);
        selected = ingredient;//select the new one
        this.openModal();
        
    }
    
    @Command @NotifyChange("selected")
    public void saveProductType(){
        getProductTypeService().saveOrUpdate(selected);
        int idx = this.getProductTypes().indexOf(selected);
        if(idx>=0){
            this.getProductTypes().remove(selected);
            this.getProductTypes().add(idx, selected);
        }else{
            this.getProductTypes().add(selected);
        }
        this.productTypeModal.setVisible(false);
    }
    
    @Command @NotifyChange({"selected","productTypes", "deleteMessage"})
    public void deleteProductType(){
        this.getProductTypeService().delete(selected.getId());//delete selected
        this.getProductTypes().remove(selected);
        selected = null; //clean the selected
        this.deleteMessage=null;
    }

    @Command @NotifyChange({"selected","productTypes", "deleteMessage"})
    public void confirmDelete2(@BindingParam("item") ProductType item ) {
         this.selected=item;
        deleteMessage = "Do you want to delete "+selected.getName()+" ?";
       
    }
    
   
    @Command
    public void cerrarModal(BindContext ctx){
            ctx.getTriggerEvent().stopPropagation();
          this.productTypeModal.setVisible(false); 
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

    public ProductTypeService getProductTypeService() {
        return productTypeService;
    }

    public void setProductTypeService(ProductTypeService ProductTypeService) {
        this.productTypeService = ProductTypeService;
    }

    
}
