/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.beans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author coslit
 */
public class Profile implements Serializable{

    private Integer id;
    private String name;
    private String description;
    
    private Set<Role> Roles = new HashSet<Role>();

    public Set<Role> getRoles() {
        return Roles;
    }

    public void setRoles(Set<Role> Roles) {
        this.Roles = Roles;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
