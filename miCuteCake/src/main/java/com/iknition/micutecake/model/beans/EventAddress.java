/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author coslit
 */
@Entity
@Table(name="event_addr")
public class EventAddress implements Serializable {
    
    @Id
    @Column(name = "idevent_addr")
    @GeneratedValue
    private Integer id;
    
    @Column
    private String alia;
    @Column
    private String address;
    @Column
    private String map;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlia() {
        return alia;
    }

    public void setAlia(String alia) {
        this.alia = alia;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }
    
    
}
