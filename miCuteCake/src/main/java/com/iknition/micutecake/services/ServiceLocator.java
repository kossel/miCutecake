/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.zkoss.zkplus.spring.SpringUtil;

/**
 *
 * @author Yichao
 */
public class ServiceLocator {
     private ServiceLocator() {}
 
    public static <T> T getBean(Class<T> clase) {
        System.out.println("bean name "+clase.getSimpleName());
        return (T)SpringUtil.getBean(clase.getSimpleName(), clase);
    }
    public static ProductTypeService getCompanyManager() {
                System.out.println("singletoooon");
		return (ProductTypeService) SpringUtil.getBean("productTypeService", ProductTypeService.class);
	}
}
