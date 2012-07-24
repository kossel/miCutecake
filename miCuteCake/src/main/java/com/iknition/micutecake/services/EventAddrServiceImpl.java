/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.EventAddress;
import com.iknition.micutecake.model.daos.ClientDao;
import com.iknition.micutecake.model.daos.EventAddrDao;
import java.io.Serializable;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author coslit
 */
@Service("eventAddrService")
public class EventAddrServiceImpl extends GenericServiceImpl<EventAddress, Integer> implements EventAddrService {
    
     
    private EventAddrDao eventAddrDao;

    public EventAddrDao getEventAddrDao() {
        return eventAddrDao;
    }

    @Resource
    public void setEventAddrDao(EventAddrDao eventAddrDao) {
        this.eventAddrDao = eventAddrDao;
        this.dao = eventAddrDao;
    }
     
}
