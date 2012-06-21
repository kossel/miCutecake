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
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author coslit
 */
public abstract class GenericDaoImpl <T extends Serializable, 
                                        KeyType extends Serializable>
                                        //extends HibernateDaoSupport
{

    protected Class<T> domainClass = getDomainClass();
     
    @Autowired
    private SessionFactory sessionFactory;
    
    public GenericDaoImpl() {
        this.domainClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
     }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    /**
     * Method to return the class of the domain object
     */
    protected Class<T> getDomainClass(){
        return this.domainClass;
    };
    
 
    public T load(KeyType id) {
    //    Session s = this.getSessionFactory().getCurrentSession();
        return (T) this.getSessionFactory().getCurrentSession().get(domainClass, id);
    }

    public void update(T t) {
        this.getSessionFactory().getCurrentSession().update(t);
    }

    public void save(T t) {
        this.getSessionFactory().getCurrentSession().save(t);
    }
    
    public void saveOrUpdate(T t) {
        this.getSessionFactory().getCurrentSession().saveOrUpdate(t);
    }
    
    public T merge(T t) {
        return (T) this.getSessionFactory().getCurrentSession().merge(t);
    }

    public void delete(T t) {
        this.getSessionFactory().getCurrentSession().delete(t);
    }

    public List<T> getList() {
        List list = getSessionFactory().getCurrentSession().createQuery("from " + domainClass.getSimpleName() + " x").list();
        System.out.println("from " + domainClass.getName() + " x");
        return list;
    }

    public void deleteById(KeyType id) {
        Object obj = load(id);
        this.getSessionFactory().getCurrentSession().delete(obj);
    }

    public boolean exists(KeyType id){
        T entity = (T) getSessionFactory().getCurrentSession().get(this.domainClass, id);
        return entity != null;
    }
    
   /* public void deleteAll() {
        getHibernateTemplate().execute(new HibernateCallback() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                String hqlDelete = "delete " + domainClass.getName();
                int deletedEntities = session.createQuery(hqlDelete).executeUpdate();
                return null;
            }

        });
    }*/

    public int count() {
        List list = this.getSessionFactory().getCurrentSession().createQuery(
                "select count(*) from " + domainClass.getName() + " x").list();
        Integer count = (Integer) list.get(0);
        return count.intValue();
    }


}
