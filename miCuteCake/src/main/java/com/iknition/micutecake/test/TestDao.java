/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.test;

import com.iknition.micutecake.model.beans.ProductType;
import com.iknition.micutecake.services.ProductTypeService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Yichao
 */
public class TestDao {

    public static void main( String[] args )
    {
    	ApplicationContext appContext = 
    	  new ClassPathXmlApplicationContext("classpath:/spring/applicationContext.xml");
 
    	ProductTypeService bo = (ProductTypeService)appContext.getBean("ProductTypeService");
 
    	/** insert **/
    	List<ProductType> list;
        list = bo.getAll();
    	System.out.println(list.size());
    }
}
