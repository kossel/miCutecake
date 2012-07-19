/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.iknition.micutecake.model.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Component;

/**
 *
 * @author coslit
 */
@Entity
@Table(name="products")
@Component("product")
public class Product implements Serializable{
    
    @Id
    @Column(name="idproducts")
    @GeneratedValue
    private Integer id;
    
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    
    @ManyToOne(fetch=FetchType.EAGER, optional=false)
    @JoinColumn(name="idproduct_type")
    private ProductType productType;

    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="product")
    private List<Recipe> recipes;

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
    
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    
}
