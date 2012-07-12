/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author coslit
 */
@Entity
@Table(name="ingredients")
public class Ingredient implements Serializable{
    
    @Id
    @Column(name = "idingredient")
    @GeneratedValue
    private Integer id;
    @Column
    private String name;
    @Column
    private BigDecimal price;
    @Column
    private String description;
    @Column
    private int quantity;
    @Column
    private String unit;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ingredient other = (Ingredient) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if (this.price != other.price && (this.price == null || !this.price.equals(other.price))) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if ((this.unit == null) ? (other.unit != null) : !this.unit.equals(other.unit)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 79 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 79 * hash + (this.price != null ? this.price.hashCode() : 0);
        hash = 79 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 79 * hash + this.quantity;
        hash = 79 * hash + (this.unit != null ? this.unit.hashCode() : 0);
        return hash;
    }

    
}
