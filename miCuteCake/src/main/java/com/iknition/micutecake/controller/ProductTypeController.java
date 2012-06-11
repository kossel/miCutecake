/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.controller;

import com.iknition.micutecake.controller.renderers.ProductTypeListRenderer;
import com.iknition.micutecake.services.ProductTypeService;
import com.iknition.micutecake.services.ServiceLocator;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

/**
 *
 * @author Yichao
 */
@Controller
public class ProductTypeController extends GenericForwardComposer{
    
    @Autowired
    private ProductTypeService ptService;
   // private AnnotateDataBinder binder;
     private Listbox typeList;
 
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
      //  binder = (AnnotateDataBinder) page.getAttribute("binder");
        this.ptService = ServiceLocator.getBean(ProductTypeService.class);
        System.out.println("servi: "+ptService);
        System.out.println("listbos "+typeList);
        
      //  this.typeList=(Listbox)comp.getFellowIfAny("typeList");
        
        typeList.setModel(new ListModelList(ptService.getAll()));
        typeList.setItemRenderer(new ProductTypeListRenderer());
    }

    public ProductTypeService getPtService() {
        return ptService;
    }

    public void setPtService(ProductTypeService ptService) {
        this.ptService = ptService;
    }

    public Listbox getTypeList() {
        return typeList;
    }

    public void setTypeList(Listbox typeList) {
        this.typeList = typeList;
    }
    
    
    
    
}
