/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.Client;
import com.iknition.micutecake.services.ClientService;
import com.iknition.micutecake.services.ConceptTypeService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.BindComposer;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
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
public class ClientVM{
    private ListModelList<Client> clients;
    private Client selected;
    private String deleteMessage;
    
    @Wire("#clientModal")
    private Window clientModal;
    
    @WireVariable
    private ClientService clientService;

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
    
    public ListModelList<Client> getClients() {
        if (clients == null) {
            ClientService s = this.getClientService();
             List a = s.getAll();
            clients = new ListModelList<Client>(a);//init the list
        }
        return clients;
    }

    
    
    public void setClients(ListModelList<Client> clients) {
        this.clients = clients;
    }


    public Client getSelected() {
        return selected;
    }

    @NotifyChange("selected")
    public void setSelected(Client selected) {
        this.selected = selected;
        this.openModal();
    }
    
    @Command
    public void openModal(){
        this.clientModal.setVisible(true);
    }
    
    @Command 
    @NotifyChange({"selected","clients"})
    public void newClient(){
        Client client = new Client();
     //   this.getConceptTypes().add(conceptType);
        selected = client;//select the new one
        this.openModal();
        
    }
    
    @Command @NotifyChange("selected")
    public void saveClient(){
        getClientService().saveOrUpdate(selected);
        int idx = getClients().indexOf(selected);
        if(idx>=0){
            this.getClients().remove(selected);
            this.getClients().add(idx, selected);
        }else{
            this.getClients().add(selected);
        }
        this.clientModal.setVisible(false);
    }
    
    @Command @NotifyChange({"selected","clients", "deleteMessage"})
    public void deleteClient(){
        this.getClientService().delete(selected.getId());//delete selected
        this.getClients().remove(selected);
        selected = null; //clean the selected
        this.deleteMessage=null;
    }

    @Command @NotifyChange({"selected","clients", "deleteMessage"})
    public void confirmDelete2(@BindingParam("item") Client item ) {
         this.selected=item;
        deleteMessage = "Do you want to delete "+selected.getId()+" ?";
       
    }
    
    @Command
    public void goToFacebook(@BindingParam("item") Client item){
        String faceAddr = item.getFacebook();
        Executions.sendRedirect("http://"+faceAddr);
    }
    
    @Command
    public void cerrarModal(BindContext ctx){
            ctx.getTriggerEvent().stopPropagation();
          this.clientModal.setVisible(false); 
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
 
  

    public String getDeleteMessage() {
        return deleteMessage;
    }

    public void setDeleteMessage(String deleteMessage) {
        this.deleteMessage = deleteMessage;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

    

    
}
