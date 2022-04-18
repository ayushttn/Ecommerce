package com.ecommerce.Controllers;

import com.ecommerce.Handler.ResponseHandler;
import com.ecommerce.Repos.OauthAccessTokenRepository;
import com.ecommerce.Service.SellerService;
import com.ecommerce.dto.AddressDto;
import com.ecommerce.dto.CustomerDto;
import com.ecommerce.dto.SellerDto;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.sql.Blob;
import java.util.List;

@RestController
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/viewProfile")
    public List<Object []> viewSellerProfile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return sellerService.getMyData(email);
    }

    @PutMapping( "/updateSellerProfile")
    public ResponseEntity<Object> updateProfile(@RequestBody SellerDto sellerDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        sellerService.updateProfile(email, sellerDto);
        return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.OK);
    }

    @PutMapping("/updateSellerPassword")
    public ResponseEntity<Object> updateSellerPassword(@Valid @RequestBody SellerDto sellerDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        if(sellerDto.getPassword().equals(sellerDto.getConfirmPassword())) {
            sellerService.updateMyPassword(email, sellerDto);
            return ResponseHandler.generateResponse("Password Updated!!!", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("Confirm Password and password are not same",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping("/updateSellerAddress/{id}")
    public ResponseEntity<Object> addAddress(@PathVariable Long id, @RequestBody AddressDto addressDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        sellerService.updateAddress(email,addressDto,id);
        return ResponseHandler.generateResponse("Address Updated Successfully", HttpStatus.OK);
    }
}
