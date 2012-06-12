/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.controller;

import com.iknition.micutecake.controller.renderers.ProductTypeListRenderer;
import com.iknition.micutecake.services.ProductTypeService;
import com.iknition.micutecake.services.ServiceLocator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

/**
 *
 * @author Yichao
 */
@Controller
public class ProductTypeController extends SelectorComposer {

    
    @Resource
    private ProductTypeService productTypeService;
   // private AnnotateDataBinder binder;
    @Wire
    private Listbox typeList;
 
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
      //  binder = (AnnotateDataBinder) page.getAttribute("binder");
   //     this.productTypeService = ServiceLocator.getBean(ProductTypeService.class);
     //   System.out.println("insta: "+comp.getFellowIfAny("typeList").getWidgetClass());
       // loadAll((Listbox)comp.getFellowIfAny("typeList"));
          System.out.println("listbos "+typeList);
          System.out.println("servi: "+productTypeService);
      //  this.typeList=(Listbox)comp.getFellowIfAny("typeList");
        
        typeList.setModel(new ListModelList(productTypeService.getAll()));
        typeList.setItemRenderer(new ProductTypeListRenderer());
    }
    
    
    public void onCreate(Listbox lb){
        List lista = productTypeService.getAll();
        System.out.println("tamano on create "+lista.size());
        BindingListModelList model = new BindingListModelList(lista, false);
        lb.setItemRenderer(new ProductTypeListRenderer());
        lb.setModel(model); 
    }
    
    @Listen("onAfterRender = listbox#typeList")
    public void bam(){
        System.out.println("BAMMMM ");
    }
    
    
    public void loadAll(Listbox lb){
        System.out.println("servi: "+productTypeService);
         System.out.println("listbos "+lb);
         List lista = productTypeService.getAll();
         System.out.println("tam "+lista.size());
        BindingListModelList model = new BindingListModelList(lista, false);
        lb.setItemRenderer(new ProductTypeListRenderer());
        lb.setModel(model);              
    }
    
    @Listen("onClick = button#load")
    public void loadAll(){
        System.out.println("servi: "+productTypeService);
         System.out.println("listbos "+typeList);
        BindingListModelList model = new BindingListModelList(productTypeService.getAll(), false);
        
        typeList.setItemRenderer(new ProductTypeListRenderer());
        typeList.setModel(model);              
    }
    
  //  @Listen("onCreate = listbox#typeList")
    public void cargar() {
        System.out.println("servi: "+productTypeService);
        System.out.println("listbos "+typeList);
        BindingListModelList model = new BindingListModelList(productTypeService.getAll(), false);
        
        typeList.setItemRenderer(new ProductTypeListRenderer());
        typeList.setModel(model);   
    }
    
    public Listbox getTypeList() {
        return typeList;
    }

    public void setTypeList(Listbox typeList) {
        this.typeList = typeList;
    }
    
    
    
    
}
