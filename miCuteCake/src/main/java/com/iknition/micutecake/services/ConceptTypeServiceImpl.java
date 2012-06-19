/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.ConceptType;
import com.iknition.micutecake.model.daos.ConceptTypeDao;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author coslit
 */
@Service("conceptTypeService")
public class ConceptTypeServiceImpl implements ConceptTypeService{

    @Resource
    ConceptTypeDao conceptTypeDao;
    
    @Override
    @Transactional(readOnly =true)
    public List getAll() {

        List result = conceptTypeDao.getList();
        return result;
    }

    @Override
    @Transactional
    public void save(ConceptType conceptType) {
        this.conceptTypeDao.saveOrUpdate(conceptType);
    }

    @Override
    @Transactional(readOnly = true)
    public ConceptType getById(Integer id) {
        return this.conceptTypeDao.load(id);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
       this.conceptTypeDao.deleteById(id);
    }

    public ConceptTypeDao getConceptTypeDao() {
        return conceptTypeDao;
    }

    public void setConceptTypeDao(ConceptTypeDao conceptTypeDao) {
        this.conceptTypeDao = conceptTypeDao;
    }
    
    
}
