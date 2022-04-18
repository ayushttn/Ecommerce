package com.ecommerce.Entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CategoryMetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "categoryMetaData")
    private Set<CategoryMetadataFieldValue> categoryMetadataFieldValues = new HashSet<>();

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

    public Set<CategoryMetadataFieldValue> getCategoryMetadataFieldValues() {
        return categoryMetadataFieldValues;
    }

    public void setCategoryMetadataFieldValues(Set<CategoryMetadataFieldValue> categoryMetadataFieldValues) {
        this.categoryMetadataFieldValues = categoryMetadataFieldValues;
    }
}
