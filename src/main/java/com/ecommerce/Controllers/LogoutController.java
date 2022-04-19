package com.ecommerce.Controllers;

import com.ecommerce.Handler.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@RestController
public class LogoutController{

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    public LogoutController(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @RequestMapping(value = "/logoutUser", method = RequestMethod.POST)
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
            return ResponseHandler.generateResponse("Logout Successfull", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("Invalid token", HttpStatus.BAD_REQUEST);
        }
    }
}
