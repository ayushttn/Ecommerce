package com.ecommerce.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> product;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "parentCategoryId" , referencedColumnName = "id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private Set<CategoryMetadataFieldValue> categoryMetadataFieldValues= new HashSet<>();

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

    public Set<Product> getProduct() {
        return product;
    }

    public void setProduct(Set<Product> product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<CategoryMetadataFieldValue> getCategoryMetadataFieldValues() {
        return categoryMetadataFieldValues;
    }

    public void setCategoryMetadataFieldValues(Set<CategoryMetadataFieldValue> categoryMetadataFieldValues) {
        this.categoryMetadataFieldValues = categoryMetadataFieldValues;
    }
}
