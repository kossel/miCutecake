/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author coslit
 */
public interface GenericService<T, PK extends Serializable>  {
    List<T> getAll();

    T getById(PK id);

    boolean exists(PK id);

    void save(T object);
    void saveOrUpdate(T object);
     T merge(T object);

    void delete(PK id);
}
