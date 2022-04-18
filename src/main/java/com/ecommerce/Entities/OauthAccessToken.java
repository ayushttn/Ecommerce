package com.ecommerce.Entities;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class OauthAccessToken {

    @Id
    private String tokenId;

    @Lob
    private Blob token;

    private String authenticationId;

    private String  userName;

    private String clientId;

    @Lob
    private Blob authentication;

    private String refreshToken;


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

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Blob getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
