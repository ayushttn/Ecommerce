package com.ecommerce;

import com.ecommerce.Entities.*;
import com.ecommerce.Repos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class EcommerceApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;


    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoleRepository roleRepository;


//    @Test
//    public void createSeller(){
//        User user = new User();
//        user.setFirstName("Ayush");
//        user.setLastName("Tyagi");
//        user.setEmail("ayushtyagi560@gmail.com");
//        Role role = new Role();
//        role.setAuthority("Seller");
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//        user.setRoles(roleSet);
//        Address address = new Address();
//        address.setCity("Delhi");
//        address.setCountry("India");
//        address.setState("Delhi");
//        address.setAddressLine("Burari");
//        address.setLabel("Office");
//        address.setZipCode(110084);
//        Address address1 = new Address();
//        address1.setCity("Lucknow");
//        address1.setAddressLine("dsfhisdhfij");
//        address1.setZipCode(123434);
//        user.addAddress(address);
//        user.addAddress(address1);
//        Seller seller = new Seller();
//        seller.setUser(user);
//        seller.setCompanyContact(328453634l);
//        seller.setCompanyName("sdffgsd");
//        user.setSeller(seller);
//        Set<User> users = new HashSet<>();
//        users.add(user);
//        role.setUsers(users);
//        userRepository.save(user);
//        addressRepository.save(address);
//        roleRepository.save(role);
//        sellerRepository.save(seller);
//    }
}
