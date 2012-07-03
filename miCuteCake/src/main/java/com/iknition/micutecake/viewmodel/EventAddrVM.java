/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.EventAddress;
import com.iknition.micutecake.model.beans.EventAddress;
import com.iknition.micutecake.services.EventAddrService;
import com.iknition.micutecake.services.EventAddrService;
import java.util.List;
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
public class EventAddrVM {
    private ListModelList<EventAddress> eventAddrs;
    private EventAddress selected;
    private String deleteMessage;
    
    @Wire("#eventAddrModal")
    private Window eventAddrModal;
    
    @WireVariable
    private EventAddrService eventAddrService;

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
    
    public ListModelList<EventAddress> getEventAddrs() {
        if (eventAddrs == null) {
            EventAddrService s = this.getEventAddrService();
             List a = s.getAll();
            eventAddrs = new ListModelList<EventAddress>(a);//init the list
        }
        return eventAddrs;
    }

    
    @NotifyChange("selected")
    public void setSelected(EventAddress selected) {
        this.selected = selected;
        this.openModal();
    }

    public EventAddress getSelected() {
        return selected;
    }
    
    
    
    @Command
    public void openModal(){
        this.eventAddrModal.setVisible(true);
    }
    
    @Command 
    @NotifyChange({"selected","eventAddrs"})
    public void newEventAddr(){
        EventAddress eventAddr = new EventAddress();
     //   this.getConceptTypes().add(conceptType);
        selected = eventAddr;//select the new one
        this.openModal();
        
    }
    
    @Command @NotifyChange("selected")
    public void saveEventAddr(){
        getEventAddrService().saveOrUpdate(selected);
        int idx = this.getEventAddrs().indexOf(selected);
        if(idx>=0){
            this.getEventAddrs().remove(selected);
            this.getEventAddrs().add(idx, selected);
        }else{
            this.getEventAddrs().add(selected);
        }
        this.eventAddrModal.setVisible(false);
    }
    
    @Command @NotifyChange({"selected","eventAddrs", "deleteMessage"})
    public void deleteEventAddr(){
        this.getEventAddrService().delete(selected.getId());//delete selected
        this.getEventAddrs().remove(selected);
        selected = null; //clean the selected
        this.deleteMessage=null;
    }

    @Command @NotifyChange({"selected","eventAddrs", "deleteMessage"})
    public void confirmDelete2(@BindingParam("item") EventAddress item ) {
         this.selected=item;
        deleteMessage = "Do you want to delete "+selected.getAlia()+" ?";
       
    }
    
    @Command
    public void goToGoogleMap(@BindingParam("item") EventAddress item){
        String googlemapAddr = item.getMap();
        Executions.sendRedirect("http://"+googlemapAddr);
    }
    
    @Command
    public void cerrarModal(BindContext ctx){
            ctx.getTriggerEvent().stopPropagation();
          this.eventAddrModal.setVisible(false); 
    }
    
    @Command @NotifyChange("deleteMessage")
    public void confirmDelete(){
        //set the message to show to user
        deleteMessage = "Do you want to delete "+selected.getAlia()+" ?";
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

    public EventAddrService getEventAddrService() {
        return eventAddrService;
    }

    public void setEventAddrService(EventAddrService eventAddrService) {
        this.eventAddrService = eventAddrService;
    }

    
    
    
}
