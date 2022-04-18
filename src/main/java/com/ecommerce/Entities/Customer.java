package com.ecommerce.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Customer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    @NotNull
    private Long contact;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private Set<Orders> order;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private ProductReview productReview;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "customer")
    private Cart cart;

    public Long getContact() {
        return contact;
    }

    public void setContact(Long contact) {
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
