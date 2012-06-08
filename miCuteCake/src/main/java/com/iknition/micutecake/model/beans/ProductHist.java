/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author coslit
 */
public class ProductHist implements Serializable{
    
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Order order;
    private int quantity;
    private Set<IngredientHist> ingredients = new HashSet<IngredientHist>();

    public Set<IngredientHist> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<IngredientHist> ingredients) {
        this.ingredients = ingredients;
    }
    
    
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    
}
