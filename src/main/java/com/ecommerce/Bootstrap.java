package com.ecommerce;

import com.ecommerce.Entities.Address;
import com.ecommerce.Entities.Role;
import com.ecommerce.Entities.User;
import com.ecommerce.Repos.RoleRepository;
import com.ecommerce.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class Bootstrap implements ApplicationRunner {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(Objects.isNull((roleRepo.findByAuthority("ROLE_ADMIN")))){
            Role role = new Role();
            role.setAuthority("ROLE_ADMIN");
            roleRepo.save(role);
        }
        if(Objects.isNull((roleRepo.findByAuthority("ROLE_SELLER")))){
            Role role = new Role();
            role.setAuthority("ROLE_SELLER");
            roleRepo.save(role);
        }
        if(Objects.isNull((roleRepo.findByAuthority("ROLE_CUSTOMER")))){
            Role role = new Role();
            role.setAuthority("ROLE_CUSTOMER");
            roleRepo.save(role);
        }
        if(Objects.isNull(userRepo.findByEmail("ayush.tyagi2@tothenew.com"))) {
            User user = new User();
            user.setFirstName("Ayush");
            user.setLastName("Tyagi");
            user.setEmail("ayush.tyagi2@tothenew.com");
            user.setPassword(passwordEncoder.encode("Ayush@1111"));
            user.setActive(true);
            user.setDelete(false);
            user.setExpired(false);
            user.setLocked(false);
            Address address = new Address();
            address.setCity("New Delhi");
            address.setState("Delhi");
            address.setCountry("India");
            address.setAddressLine("Burari");
            address.setZipCode(110084);
            address.setLabel("Office");
            user.addAddress(address);
            Role role = roleRepo.findByAuthority("ROLE_ADMIN");
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            user.setRoles(roles);
            userRepo.save(user);
        }
    }
}