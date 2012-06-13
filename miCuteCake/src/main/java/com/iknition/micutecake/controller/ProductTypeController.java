/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.controller;

import com.iknition.micutecake.controller.renderers.ProductTypeListRenderer;
import com.iknition.micutecake.model.beans.ProductType;
import com.iknition.micutecake.services.ProductTypeService;
import com.iknition.micutecake.services.ServiceLocator;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.*;

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
    @Wire
    private Textbox txtname;
    
    @Wire
    private ProductType productType;

    
    
    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        typeList.setModel(new ListModelList(productTypeService.getAll()));
        typeList.setItemRenderer(new ProductTypeListRenderer());
        productType = new ProductType();
    }

    @Listen("onClick = button#load")
    public void loadAll(){
        BindingListModelList model = new BindingListModelList(productTypeService.getAll(), false);
        typeList.setItemRenderer(new ProductTypeListRenderer());
        typeList.setModel(model);              
    }
    
     @Listen("onClick = #new")
    public void showModal(Event e) {
        Component comp = Executions.createComponents(
                "/new.zul", null, null);
        if(comp instanceof Window) {
            ((Window)comp).doModal();
        }
    }
     
     @Listen("onClick =#save")
     public void save() throws InterruptedException {
                ProductType p =(ProductType)SpringUtil.getBean("productType");
		System.out.println("bean: " + this.getProductType().getName());
                System.out.println("en txbox: "+txtname.getText());
	}

    public Listbox getTypeList() {
        return typeList;
    }

    public void setTypeList(Listbox typeList) {
        this.typeList = typeList;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
    
    
    
    
}
