/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.beans;

import java.math.BigDecimal;
import javax.persistence.*;

/**
 *
 * @author coslit
 */
@Entity
@Table(name = "ele_recipe")
public class EleRecipe {
    
    @Id
    @GeneratedValue
    @Column(name = "id_ele_recipe")
    private Integer id;
    @Column
    private BigDecimal quantity;
    @Column
    private String unit;
    
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idRecipe")
    private Recipe recipe;
    
    @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idIngredient")
    private Ingredient ingredient;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    
}
