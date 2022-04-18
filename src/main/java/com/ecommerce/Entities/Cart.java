package com.ecommerce.Entities;

import javax.persistence.*;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_user_id")
    private Customer customer;

    private Integer quantity;

    private Boolean isWishlistItem;

    @OneToOne
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getWishlistItem() {
        return isWishlistItem;
    }

    public void setWishlistItem(Boolean wishlistItem) {
        isWishlistItem = wishlistItem;
    }

    public ProductVariation getProductVariation() {
        return productVariation;
    }

    public void setProductVariation(ProductVariation productVariation) {
        this.productVariation = productVariation;
    }
}
