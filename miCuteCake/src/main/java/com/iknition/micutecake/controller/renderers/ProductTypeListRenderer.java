/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.controller.renderers;

import com.iknition.micutecake.model.beans.ProductType;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 *
 * @author Yichao
 */
public class ProductTypeListRenderer implements ListitemRenderer{
    public void render(Listitem item, Object data, int index) throws Exception {
        ProductType pType = (ProductType) data;
        item.setValue(pType);
        new Listcell(String.valueOf(pType.getId())).setParent(item);
        new Listcell(pType.getName()).setParent(item);
        new Listcell(pType.getDescription()).setParent(item);
    }
}
