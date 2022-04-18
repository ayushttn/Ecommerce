package com.ecommerce.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private Seller seller;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductVariation> productVariation;

    @NotNull
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Boolean isCancellable;
    private Boolean isReturnable;
    @NotNull
    private String brand;
    private Boolean isActive;
    private Boolean isDeleted;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private Set<ProductReview> productReview;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Set<ProductVariation> getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(Set<ProductVariation> productVariation) {
        this.productVariation = productVariation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getCancellable() {
        return isCancellable;
    }

    public void setCancellable(Boolean cancellable) {
        isCancellable = cancellable;
    }

    public Boolean getReturnable() {
        return isReturnable;
    }

    public void setReturnable(Boolean returnable) {
        isReturnable = returnable;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Set<ProductReview> getProductReview() {
        return productReview;
    }

    public void setProductReview(Set<ProductReview> productReview) {
        this.productReview = productReview;
    }
}
