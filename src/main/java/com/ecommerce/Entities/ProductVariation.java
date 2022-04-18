package com.ecommerce.Entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class ProductVariation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productVariation")
    private OrderProduct orderProduct;

    @NotNull
    private Integer quantityAvailable;
    @NotNull
    private Integer price;
    private String primaryImageName;
    private Boolean isActive;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productVariation")
    private Cart cart;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrderProduct getOrderProduct() {
        return orderProduct;
    }

    public void setOrderProduct(OrderProduct orderProduct) {
        this.orderProduct = orderProduct;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPrimaryImageName() {
        return primaryImageName;
    }

    public void setPrimaryImageName(String primaryImageName) {
        this.primaryImageName = primaryImageName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
