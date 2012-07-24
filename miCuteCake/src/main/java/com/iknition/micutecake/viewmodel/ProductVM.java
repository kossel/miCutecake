/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.Product;
import com.iknition.micutecake.model.beans.ProductType;
import com.iknition.micutecake.model.beans.Recipe;
import com.iknition.micutecake.services.ProductService;
import com.iknition.micutecake.services.ProductTypeService;
import com.iknition.micutecake.services.RecipeService;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

/**
 *
 * @author coslit
 */
public class ProductVM {
    private ListModelList<Product> products;
    private ListModelList<ProductType> productTypes;
    private ListModelList<Recipe> recipes;
    
    private Product selected;
    private Recipe selectedRecipe;
    private String deleteMessage;
    
    @Wire("#productModal")
    private Window productModal;
    
    @WireVariable
    private ProductService productService;
    
    @WireVariable
    private ProductTypeService productTypeService;
    
    @WireVariable
    private RecipeService recipeService;

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

    
    @NotifyChange({"selected","recipes"})
    public void setSelected(Product selected) {
        this.selected = this.getProductService().getProductWithRecipes(selected.getId());
        List a = this.selected.getRecipes();
        this.recipes = new ListModelList<Recipe>(a);
        this.openModal();
    }

    public Product getSelected() {
        return selected;
    }

    public ListModelList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(ListModelList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Command
    public void openModal(){
        this.productModal.setVisible(true);
    }
    
    @Command
    public void confirmDeleteRecipe(@BindingParam("item") final Recipe item){
        Messagebox.show("Estas seguro que desea borrar recepta " + item.getName()+"?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                new org.zkoss.zk.ui.event.EventListener(){
                    public void onEvent(Event e){
                        if(Messagebox.ON_OK.equals(e.getName())){
                            deleteRecipe(item);
                        }else if(Messagebox.ON_CANCEL.equals(e.getName())){
                           System.out.println(" fue cancel ");
                        }
                    }
        }
                );
    }
    
    @Command 
    @NotifyChange({"selected","products"})
    public void newProduct(){
        Product product = new Product();
     //this.getConceptTypes().add(conceptType);
        selected = product;//select the new one
        this.openModal();
        
    }
    
    @Command @NotifyChange("selected")
    public void saveProduct(){
        getProductService().saveOrUpdate(selected);
        int idx = this.getProducts().indexOf(selected);
        if(idx>=0){
            boolean a = this.getProducts().remove(selected);
            System.out.println("system "+ a);
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

    @NotifyChange({"recipes","selected"})
    public void deleteRecipe(Recipe r){
        //delete with service
     //   recipeService.delete(r.getId());
        this.selected.getRecipes().remove(r);
        this.recipes.remove(r);
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
 
    @Command
    public void openDetailRecipeModal() {
        Component comp = Executions.createComponents("/product/eleRecipe.zul", null, null);
        if(comp instanceof Window) {
            ((Window)comp).doModal();
        }
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

    public Recipe getSelectedRecipe() {
        return selectedRecipe;
    }

    public void setSelectedRecipe(Recipe selectedRecipe) {
        this.selectedRecipe = selectedRecipe;
    }
    
    
    
}
