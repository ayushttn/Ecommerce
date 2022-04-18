package com.ecommerce.Controllers;
import com.ecommerce.Entities.Customer;
import com.ecommerce.Entities.Seller;
import com.ecommerce.Entities.User;
import com.ecommerce.Handler.ResponseHandler;
import com.ecommerce.Repos.CustomerRepository;
import com.ecommerce.Repos.SellerRepository;
import com.ecommerce.Repos.UserRepository;
import com.ecommerce.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @GetMapping("/allCustomers")
    public List<Object []> getAllCustomers(){
        return adminService.viewAllCustomers();
    }

    @GetMapping("/allSellers")
    public List<Object []> getAllSellers(){
        return adminService.viewAllSeller();
    }

    @PutMapping(value = "/activateCustomer/{id}")
    public ResponseEntity<Object> activateCustomer(@PathVariable("id") Long id){
        Customer customer=customerRepository.findById(id).get();
        User user = customer.getUser();
        if(customer==null){
            return ResponseHandler.generateResponse("User Not Found", HttpStatus.NOT_FOUND);
        }
        else if(user.getActive()){
            return ResponseHandler.generateResponse("User is Already Active", HttpStatus.BAD_REQUEST);
        }
        else {
            User customer1=adminService.activateUser(user);
            return ResponseHandler.generateResponse("User Activated Successfully", HttpStatus.OK);
        }
    }

    @PutMapping(value = "/deactivateCustomer/{id}")
    public ResponseEntity<Object> deactivateCustomer(@PathVariable("id") Long id){
        Customer customer=customerRepository.findById(id).get();
        User user = customer.getUser();
        if(customer==null){
            return ResponseHandler.generateResponse("User Not Found", HttpStatus.NOT_FOUND);
        }
        else if(user.getActive()){
            User customer1=adminService.deactivateUser(user);
            return ResponseHandler.generateResponse("User Deactivated Successfully", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("User is Not Active", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/activateSeller/{id}")
    public ResponseEntity<Object> activateSeller(@PathVariable("id") Long id){
        Seller seller=sellerRepository.findById(id).get();
        User user = seller.getUser();
        if(seller==null){
            return ResponseHandler.generateResponse("User Not Found", HttpStatus.NOT_FOUND);
        }
        else if(user.getActive()){
            return ResponseHandler.generateResponse("User is Already Active", HttpStatus.BAD_REQUEST);
        }
        else {
            User customer1=adminService.activateUser(user);
            return ResponseHandler.generateResponse("User Activated Successfully", HttpStatus.OK);
        }
    }

    @PutMapping(value = "/deactivateSeller/{id}")
    public ResponseEntity<Object> deactivateSeller(@PathVariable("id") Long id){
        Seller seller=sellerRepository.findById(id).get();
        User user = seller.getUser();
        if(seller==null){
            return ResponseHandler.generateResponse("User Not Found", HttpStatus.NOT_FOUND);
        }
        else if(user.getActive()){
            User customer1=adminService.deactivateUser(user);
            return ResponseHandler.generateResponse("User Deactivated Successfully", HttpStatus.OK);
        }
        else {
            return ResponseHandler.generateResponse("User is Not Active", HttpStatus.BAD_REQUEST);
        }
    }
}
