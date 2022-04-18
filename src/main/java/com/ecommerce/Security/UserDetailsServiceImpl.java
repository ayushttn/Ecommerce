package com.ecommerce.Security;

import com.ecommerce.Entities.User;
import com.ecommerce.Exceptions.UserNotFoundException;
import com.ecommerce.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found for email" + username);
        }
        if(user.getActive() == true) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
        }
        else {
            throw new UserNotFoundException("User Not Active");
        }
    }
}