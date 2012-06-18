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
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;

/**
 *
 * @author coslit
 */
//@Controller
public class ConceptTypeVM{
    private ListModelList<ConceptType> conceptTypes;
    private ConceptType selected;
    
  //  @Resource
    @WireVariable
    private ConceptTypeService conceptTypeService;

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
    }
    
    @Command 
    @NotifyChange({"selected","conceptTypes"})
    public void newConceptType(){
        ConceptType conceptType = new ConceptType();
        this.getConceptTypes().add(conceptType);
        selected = conceptType;//select the new one
    }
    
    @Command @NotifyChange("selected")
    public void saveConceptType(){
        getConceptTypeService().save(selected);
    }
    
    @Command @NotifyChange({"selected","conceptTypes"})
    public void deleteConceptType(){
        this.getConceptTypeService().delete(selected.getId());//delete selected
        this.getConceptTypes().remove(selected);
        selected = null; //clean the selected
    }

    public ConceptTypeService getConceptTypeService() {
        return conceptTypeService;
    }

    public void setConceptTypeService(ConceptTypeService conceptTypeService) {
        this.conceptTypeService = conceptTypeService;
    }
     
    
}
