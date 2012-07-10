/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.viewmodel;

import com.iknition.micutecake.model.beans.ProductType;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.web.servlet.dsp.action.Page;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.EventQueue;
import org.zkoss.zk.ui.event.EventQueues;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

/**
 *
 * @author coslit
 */
public class ContentVM {
    
    
    @Wire("#content")
    Include content;
       
    private EventQueue eq;
    
    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
        eq = EventQueues.lookup("interactive", EventQueues.DESKTOP, true);
        eq.subscribe(new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                String value = (String)event.getData();
                content.setSrc(value);
                System.out.println("aa -> "+content.getSrc());
            }
        });
    }
    
    
    
}
