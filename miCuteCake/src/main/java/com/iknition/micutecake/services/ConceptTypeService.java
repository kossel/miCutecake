/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.ConceptType;
import java.util.List;

/**
 *
 * @author coslit
 */
public interface ConceptTypeService {
    public List getAll();
    public void save(ConceptType productType);
    public ConceptType getById(Integer id);
    public void delete(Integer id);
}
