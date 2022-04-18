package com.ecommerce.Controllers;


import com.ecommerce.Entities.ConfirmationToken;
import com.ecommerce.Entities.User;
import com.ecommerce.Exceptions.UserNotFoundException;
import com.ecommerce.Handler.ResponseHandler;
import com.ecommerce.Repos.TokenRepository;
import com.ecommerce.Repos.UserRepository;
import com.ecommerce.Service.UserService;
import com.ecommerce.dto.UserDto;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@RestController
public class Register {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register/seller")
    public ResponseEntity<Object> sellerRegister(@Valid @RequestBody UserDto userDto) {
        if (Objects.isNull(userRepository.findByEmail(userDto.getEmail()))) {
            if (userDto.getPassword().equals(userDto.getConfirmPassword())) {
                userService.createSellerUser(userDto);
                return ResponseHandler.generateResponse("Seller User Created !!!", HttpStatus.OK);
            }
            else {
                return ResponseHandler.generateResponse("Confirm Password and password are not same",HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return ResponseHandler.generateResponse("User Already Exists!", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/register/customer")
    public ResponseEntity<Object> customerRegister(@Valid @RequestBody UserDto userDto) {
        if (Objects.isNull(userRepository.findByEmail(userDto.getEmail()))) {
            if(userDto.getPassword().equals(userDto.getConfirmPassword())) {
                userService.createCustomerUser(userDto);
                return ResponseHandler.generateResponse("Customer User Created !!!", HttpStatus.OK);
            }
            else {
                return ResponseHandler.generateResponse("Confirm Password and password are not same",HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return ResponseHandler.generateResponse("User Already Exists!", HttpStatus.BAD_REQUEST);
        }
    }
    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<Object> confirmAcount(@RequestParam("token") String confirmationToken) {
        ConfirmationToken token = tokenRepository.findByConfirmationToken(confirmationToken);
        if (token != null && token.getExpiryDate().after(new Date())){
            User user = userRepository.findByEmail(token.getUserEntity().getEmail());
            user.setActive(true);
            userRepository.save(user);
            tokenRepository.delete(token);
            return ResponseHandler.generateResponse("User Activated Successfully", HttpStatus.OK);
        } else {
            return ResponseHandler.generateResponse("Invalid Token", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/resend-activationlink")
    public ResponseEntity<Object> resendActivation(@RequestBody ObjectNode objectNode) {
        String email = objectNode.get("email").asText();
        User user = userService.resendActivationLink(email);
        if(user == null){
            //return ResponseHandler.generateResponse("User Not Found", HttpStatus.BAD_REQUEST);
            throw new UserNotFoundException("User do not Exists");
        }
        else {
            return ResponseHandler.generateResponse("Activation Link Sent!", HttpStatus.OK);
        }
    }
}
