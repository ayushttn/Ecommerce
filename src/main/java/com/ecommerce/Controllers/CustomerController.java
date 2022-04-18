package com.ecommerce.Controllers;

import com.ecommerce.Handler.ResponseHandler;
import com.ecommerce.Service.CustomerService;
import com.ecommerce.dto.AddressDto;
import com.ecommerce.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/viewMyProfile")
    public List<Object []> viewSellerProfile(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return customerService.getMyData(email);
    }

    @GetMapping("/viewMyAddresses")
    public List<Object []> viewAddress(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        return customerService.getAddress(email);
    }

    @PutMapping( "/updateProfile")
    public ResponseEntity<Object> updateProfile(@RequestBody CustomerDto customerDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        customerService.updateProfile(email, customerDto);
        return ResponseHandler.generateResponse("Updated Successfully", HttpStatus.OK);
    }

    @PutMapping("/updateMyPassword")
    public ResponseEntity<Object> updateMyPassword(@Valid @RequestBody CustomerDto customerDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        if(customerDto.getPassword().equals(customerDto.getConfirmPassword())) {
            customerService.updateMyPassword(email, customerDto);
            return ResponseHandler.generateResponse("Password Updated!!!", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("Confirm Password and password are not same",HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/addAddress")
    public ResponseEntity<Object> addAddress(@RequestBody AddressDto addressDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        customerService.addAddress(email,addressDto);
        return ResponseHandler.generateResponse("Address added Successfully", HttpStatus.OK);
    }

    @DeleteMapping("/deleteAddress/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Long id, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        customerService.deleteAddress(email, id);
        return ResponseHandler.generateResponse("Address Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/updateAddress/{id}")
    public ResponseEntity<Object> addAddress(@PathVariable Long id,@RequestBody AddressDto addressDto, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String email = principal.getName();
        customerService.updateAddress(email,addressDto,id);
        return ResponseHandler.generateResponse("Address Updated Successfully", HttpStatus.OK);
    }
}
