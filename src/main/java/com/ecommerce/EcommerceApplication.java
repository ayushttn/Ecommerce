package com.ecommerce;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


//    @Bean
//    public AuthenticationManager authenticationManager(){
//        return new AuthenticationManager() {
//            @Override
//            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//                return null;
//            }
//        };
//    }

//    @Bean
//    public JavaMailSender mailSender(){
//        return new JavaMailSenderImpl();
//    }

}
