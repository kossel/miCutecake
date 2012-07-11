/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.Product;
import com.iknition.micutecake.model.beans.ProductType;
import com.iknition.micutecake.services.ProductService;
import com.iknition.micutecake.services.ProductTypeService;
import java.util.List;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

/**
 *
 * @author coslit
 */
public class ProductVM {
    private ListModelList<Product> products;
    private ListModelList<ProductType> productTypes;
    
    private Product selected;
    private String deleteMessage;
    
    @Wire("#productModal")
    private Window productModal;
    
    @WireVariable
    private ProductService productService;
    
    @WireVariable
    private ProductTypeService productTypeService;

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        List l = productTypeService.getAll();
        this.productTypes = new ListModelList<ProductType>(l);
        System.out.println("--composed-----");
    }
    
    public ListModelList<Product> getProducts() {
        if (products == null) {
            ProductService s = this.getProductService();
             List a = s.getAll();
            products = new ListModelList<Product>(a);//init the list
        }
        return products;
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
    public void setSelected(Product selected) {
        this.selected = selected;
        this.openModal();
    }

    public Product getSelected() {
        System.out.println("getselected");
        return selected;
    }
    
    
    
    @Command
    public void openModal(){
        this.productModal.setVisible(true);
    }
    
    @Command 
    @NotifyChange({"selected","products"})
    public void newProduct(){
        Product product = new Product();
     //   this.getConceptTypes().add(conceptType);
        selected = product;//select the new one
        this.openModal();
        
    }
    
    @Command @NotifyChange("selected")
    public void saveProduct(){
        getProductService().saveOrUpdate(selected);
        int idx = this.getProducts().indexOf(selected);
        if(idx>=0){
            this.getProducts().remove(selected);
            this.getProducts().add(idx, selected);
        }else{
            this.getProducts().add(selected);
        }
        this.productModal.setVisible(false);
    }
    
    @Command @NotifyChange({"selected","products", "deleteMessage"})
    public void deleteProduct(){
        this.getProductService().delete(selected.getId());//delete selected
        this.getProducts().remove(selected);
        selected = null; //clean the selected
        this.deleteMessage=null;
    }

    @Command @NotifyChange({"selected","products", "deleteMessage"})
    public void confirmDelete2(@BindingParam("item") Product item ) {
         this.selected=item;
        deleteMessage = "Do you want to delete "+selected.getName()+" ?";
       
    }
    
   
    @Command
    public void cerrarModal(BindContext ctx){
            ctx.getTriggerEvent().stopPropagation();
          this.productModal.setVisible(false); 
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

    public ProductService getProductService() {
        return productService;
    }

    public void setProductService(ProductService ProductService) {
        this.productService = ProductService;
    }

    public ProductTypeService getProductTypeService() {
        return productTypeService;
    }

    public void setProductTypeService(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }
    
    
}
