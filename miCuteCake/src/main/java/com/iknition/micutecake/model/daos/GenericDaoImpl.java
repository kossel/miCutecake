/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.daos;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author coslit
 */
public abstract class GenericDaoImpl <T extends Serializable, 
                                        KeyType extends Serializable>
                                        extends HibernateDaoSupport{

    protected Class<T> domainClass = getDomainClass();

    public GenericDaoImpl() {
        this.domainClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
     }
    
    /**
     * Method to return the class of the domain object
     */
    protected Class<T> getDomainClass(){
        return this.domainClass;
    };

    public T load(KeyType id) {
        return (T) getHibernateTemplate().load(domainClass, id);
    }

    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    public List<T> getList() {
        return (getHibernateTemplate().find("from " + domainClass.getName() + " x"));
    }

    public void deleteById(KeyType id) {
        Object obj = load(id);
        getHibernateTemplate().delete(obj);
    }

    public void deleteAll() {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String hqlDelete = "delete " + domainClass.getName();
                int deletedEntities = session.createQuery(hqlDelete).executeUpdate();
                return null;
            }

        });
    }

    public int count() {
        List list = getHibernateTemplate().find(
                "select count(*) from " + domainClass.getName() + " x");
        Integer count = (Integer) list.get(0);
        return count.intValue();
    }


}
