/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.daos.GenericDao;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author coslit
 */
public abstract class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {
    protected final Log log = LogFactory.getLog(getClass());
    
    protected GenericDao<T, PK> dao;

    public GenericServiceImpl() {}

    public GenericServiceImpl(GenericDao<T, PK> genericDao) {
        this.dao = genericDao;
    }

    @Override
    @Transactional(readOnly=true)
    public List<T> getAll() {
        return dao.getList();
    }

    @Override
    @Transactional(readOnly=true)
    public T getById(PK id) {
        return dao.load(id);
    }

    @Override
    @Transactional(readOnly=true)
    public boolean exists(PK id) {
        return dao.exists(id);
    }

    @Override
    @Transactional
    public void save(T object) {
         dao.save(object);
    }
    
    @Override
    @Transactional
     public void saveOrUpdate(T object) {
         dao.saveOrUpdate(object);
    }
     
    @Transactional
    @Override
    public T merge(T object) {
        return dao.merge(object);
    }

    @Override
    @Transactional
    public void delete(PK id) {
        dao.deleteById(id);
    }
    
    
}
