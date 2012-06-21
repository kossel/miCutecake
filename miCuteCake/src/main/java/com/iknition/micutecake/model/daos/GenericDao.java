/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.daos;

import java.util.List;

/**
 *
 * @author coslit
 */
public interface GenericDao<DomainObject, KeyType> {

    public DomainObject load(KeyType id);
    
    public void update(DomainObject object);

    public void save(DomainObject object);

    public void saveOrUpdate(DomainObject object);
    
    public DomainObject merge(DomainObject object);
    
    public void delete(DomainObject object);
    
    public void deleteById(KeyType id);

    public List<DomainObject> getList();
    
    public boolean exists(KeyType id);
    
   // public void deleteAll();
    
    public int count();
	    
}
