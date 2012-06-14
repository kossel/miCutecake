/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.controller.renderers;

import com.iknition.micutecake.model.beans.ProductType;
import com.iknition.micutecake.services.ProductTypeService;
import java.util.List;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.*;

/**
 *
 * @author Yichao
 */
public class ProductTypeListRenderer implements ListitemRenderer{
    
     Listbox pts;
     ProductTypeService productTypeService;
     public ProductTypeListRenderer(Listbox pts, ProductTypeService pservice){
         this.pts=pts;
         this.productTypeService=pservice;
     }
    
    public void render(Listitem item, Object data, int index) throws Exception {
        final ProductType pType = (ProductType) data;
        item.setValue(pType);
        new Listcell(String.valueOf(pType.getId())).setParent(item);
        new Listcell(pType.getName()).setParent(item);
        new Listcell(pType.getDescription()).setParent(item);
        initDelBtn(pType, new Listcell()).setParent(item);
    }
    
    private Listcell initDelBtn(final ProductType pt, Listcell cell){
        cell.setImage("images/close.png");
        cell.addEventListener("onClick", new EventListener() {
            @Override
            public void onEvent(Event event) throws Exception {
                 Messagebox.show("Are you sure to delete?", "Confirm", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
                    new EventListener() {
			public void onEvent(Event event) throws Exception {
				if (((Integer) event.getData()).intValue() == Messagebox.OK) {
					((ListModelList)pts.getModel()).remove(pt);
                productTypeService.delete(pt.getId());
				}
			}
                    });
            }
        });
        return cell;
    }
    
    
   
    
    
    
}
