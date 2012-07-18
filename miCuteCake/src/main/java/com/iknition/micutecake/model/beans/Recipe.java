/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.beans;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author coslit
 */
@Entity
@Table(name="recipe")
public class Recipe {
    
    @Id
    @GeneratedValue
    @Column(name="idRecipe")
    private Integer id;
    @Column
    private String name;
    @Column
    private String Description;
    @Column
    private int quantity;
    
    @ManyToOne
    @JoinColumn(name="idproduct")
    private Product product;
    
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name="id_ele_recipe")
    private List<EleRecipe> eleRecipe;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public List<EleRecipe> getEleRecipe() {
        return eleRecipe;
    }

    public void setEleRecipe(List<EleRecipe> eleRecipe) {
        this.eleRecipe = eleRecipe;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void add(EleRecipe ele){
        this.getEleRecipe().add(ele);
    }
    
    
}
