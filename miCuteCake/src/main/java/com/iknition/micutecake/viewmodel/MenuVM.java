/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.web.servlet.dsp.action.Page;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import sun.dc.pr.PRError;

/**
 *
 * @author coslit
 */
public class MenuVM {
    
    private EventQueue eq;
    
    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }
    
   
    
    @Command
    public void gotoCatIngredient(){
        eq = EventQueues.lookup("interactive", EventQueues.DESKTOP, false);
        eq.publish(new Event("onClick", null,  "product/ingredient.zul"));
    }
    
    @Command
    public void gotoCatClient(){
        eq = EventQueues.lookup("interactive", EventQueues.DESKTOP, false);
        eq.publish(new Event("onClick", null,  "client/client.zul"));
    }
    
    @Command
    public void gotoCatConceptType(){
        eq = EventQueues.lookup("interactive", EventQueues.DESKTOP, false);
        eq.publish(new Event("onClick", null,  "event/conceptType.zul"));
    }
    
    @Command
    public void gotoCatEventAddr(){
        eq = EventQueues.lookup("interactive", EventQueues.DESKTOP, false);
        eq.publish(new Event("onClick", null,  "event/eventAddr.zul"));
    }
    
    @Command
    public void gotoCatProductType(){
        eq = EventQueues.lookup("interactive", EventQueues.DESKTOP, false);
        eq.publish(new Event("onClick", null,  "product/productType.zul"));
    }
}
