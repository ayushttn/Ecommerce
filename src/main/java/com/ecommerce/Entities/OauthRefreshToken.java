package com.ecommerce.Entities;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class OauthRefreshToken {

    @Id
    private String tokenId;

    @Lob
    private Blob token;

    @Lob
    private Blob authentication;


    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Blob getToken() {
        return token;
    }

    public void setToken(Blob token) {
        this.token = token;
    }

    public Blob getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }
}
