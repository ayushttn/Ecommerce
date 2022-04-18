package com.ecommerce.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Seller{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private User user;
    @NotNull
    private String gst;
    @NotNull
    private Long companyContact;
    @NotNull
    private String companyName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "seller")
    private Set<Product> product;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGST() {
        return gst;
    }

    public void setGST(String gst) {
        this.gst = gst;
    }

    public Long getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(Long companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
