/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.daos.GenericDao;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 *
 * @author coslit
 */
public abstract class GenericServiceImpl <T extends GenericDao, 
                                        E extends Serializable> implements GenericService<T,E>{
    
    protected Class<T> domainClass = getDomainClass();
    private T dao;
    
    public GenericServiceImpl() {
        this.domainClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
     }
    
    public void setDao(T dao) {
        this.dao = dao;
    }
    
    protected Class<T> getDomainClass(){
        return this.domainClass;
    }
    
    @Override
    public T load(E id) {
        return (T) dao.load(id);
    }
    
}
