/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.services;

import com.iknition.micutecake.model.beans.Client;
import com.iknition.micutecake.model.daos.ClientDao;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *
 * @author coslit
 */
@Service("clientService")
public class ClientServiceImpl extends GenericServiceImpl<Client, Integer> implements ClientService{

  
    private ClientDao clientDao;

    public ClientDao getClientDao() {
        return clientDao;
    }

    @Resource
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
        this.dao = clientDao;
    }
    

    
    
}
