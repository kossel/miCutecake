/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.ConceptType;
import com.iknition.micutecake.services.ConceptTypeService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.BindComposer;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

/**
 *
 * @author coslit
 */
//@Controller
public class ConceptTypeVM{
    private ListModelList<ConceptType> conceptTypes;
    private ConceptType selected;
    private String deleteMessage;
    
    @Wire("#conceptTypeModal")
    private Window conceptTypeModal;
    
    @WireVariable
    private ConceptTypeService conceptTypeService;

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
    
    public ListModelList<ConceptType> getConceptTypes() {
        if (conceptTypes == null) {
            ConceptTypeService s = this.getConceptTypeService();
             List a = s.getAll();
            conceptTypes = new ListModelList<ConceptType>(a);//init the list
        }
        return conceptTypes;
    }

    public void setConceptTypes(ListModelList<ConceptType> conceptTypes) {
        this.conceptTypes = conceptTypes;
    }


    public ConceptType getSelected() {
        return selected;
    }

    @NotifyChange("selected")
    public void setSelected(ConceptType selected) {
        this.selected = selected;
        this.conceptTypeModal.setVisible(true);
    }
    
    @Command 
    @NotifyChange({"selected","conceptTypes"})
    public void newConceptType(){
        ConceptType conceptType = new ConceptType();
     //   this.getConceptTypes().add(conceptType);
        selected = conceptType;//select the new one
        this.conceptTypeModal.setVisible(true); 
        
    }
    
    @Command @NotifyChange("selected")
    public void saveConceptType(){
        getConceptTypeService().save(selected);
        this.getConceptTypes().add(selected);
        this.conceptTypeModal.setVisible(false);
    }
    
    @Command @NotifyChange({"selected","conceptTypes", "deleteMessage"})
    public void deleteConceptType(){
        this.getConceptTypeService().delete(selected.getId());//delete selected
        this.getConceptTypes().remove(selected);
        selected = null; //clean the selected
        this.deleteMessage=null;
    }

    @Command @NotifyChange({"selected","conceptTypes", "deleteMessage"})
    public void confirmDelete2(@BindingParam("item") ConceptType item ) {
         this.selected=item;
        deleteMessage = "Do you want to delete "+selected.getId()+" ?";
       
    }
    
    @Command
    public void cerrarModal(BindContext ctx){
            ctx.getTriggerEvent().stopPropagation();
          this.conceptTypeModal.setVisible(false); 
    }
    
    @Command @NotifyChange("deleteMessage")
    public void confirmDelete(){
        //set the message to show to user
        deleteMessage = "Do you want to delete "+selected.getId()+" ?";
    }
    
    @Command @NotifyChange("deleteMessage")
    public void cancelDelete(){
        //clear the message
        deleteMessage = null;
    }
    
    public ConceptTypeService getConceptTypeService() {
        return conceptTypeService;
    }

    public void setConceptTypeService(ConceptTypeService conceptTypeService) {
        this.conceptTypeService = conceptTypeService;
    }

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(String deleteMessage) {
        this.deleteMessage = deleteMessage;
    }


    
}
