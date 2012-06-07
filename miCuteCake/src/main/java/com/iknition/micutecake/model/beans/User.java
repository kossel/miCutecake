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
public class User implements Serializable{
    private Integer id;
    private String userName;
    private boolean enabled;
    private String email;
    private Profile profile;
    private Set<Account> acount = new HashSet<Account>();
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Account> getAcount() {
        return acount;
    }

    public void setAcount(Set<Account> acount) {
        this.acount = acount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

        public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
}
