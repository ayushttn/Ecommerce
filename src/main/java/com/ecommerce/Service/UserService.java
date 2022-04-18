package com.ecommerce.Service;

import com.ecommerce.Entities.*;
import com.ecommerce.Repos.*;
import com.ecommerce.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ResetTokenRepository resetTokenRepository;


    public User createSellerUser(UserDto userDto)
    {
        User user=new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setActive(false);
        user.setDelete(false);
        user.setExpired(false);
        user.setLocked(false);
        Role role = roleRepository.findByAuthority("ROLE_SELLER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);
        Address address = new Address();
        address.setCity(userDto.getCity());
        address.setState(userDto.getState());
        address.setCountry(userDto.getCountry());
        address.setAddressLine(userDto.getAddressLine());
        address.setZipCode(userDto.getZipCode());
        address.setLabel(userDto.getLabel());
        address.setUser(user);
        Seller seller = new Seller();
        seller.setCompanyName(userDto.getCompanyName());
        seller.setCompanyContact(userDto.getCompanyContact());
        seller.setGST(userDto.getGst());
        seller.setUser(user);
        User userSave=userRepository.save(user);
        sellerRepository.save(seller);
        addressRepository.save(address);
        return userSave;
    }

    public User createCustomerUser(UserDto userDto)
    {
        User user=new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setActive(false);
        user.setDelete(false);
        user.setExpired(false);
        user.setLocked(false);
        Role role = roleRepository.findByAuthority("ROLE_CUSTOMER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);
        Address address = new Address();
        address.setCity(userDto.getCity());
        address.setState(userDto.getState());
        address.setCountry(userDto.getCountry());
        address.setAddressLine(userDto.getAddressLine());
        address.setZipCode(userDto.getZipCode());
        address.setLabel(userDto.getLabel());
        address.setUser(user);
        Customer customer = new Customer();
        customer.setContact(userDto.getContact());
        customer.setUser(user);
        User userSave=userRepository.save(user);
        customerRepository.save(customer);
        addressRepository.save(address);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        tokenRepository.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);
        return userSave;
    }

    Boolean userExist(String email){
        return userRepository.findByEmail(email) !=null ? true : false;
    }

    Boolean userIsActive(String email){
        return userRepository.findByActive(email);
    }

    public User forgotPassword(String email) {
        boolean ifExist=userExist(email);
        if(ifExist && userIsActive(email)){
            User user=userRepository.findByEmail(email);
            ResetToken resetToken = new ResetToken(user);
            resetTokenRepository.save(resetToken);
            user.setResetPasswordToken(resetToken.getResetToken());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Forgot Password!");
            mailMessage.setText("To reset your password, please click here : "
                    + "http://localhost:8080/reset-password?token=" + resetToken.getResetToken());
            emailService.sendEmail(mailMessage);
            return user;
        }
        else{
            return null;
        }
    }

    public User resendActivationLink(String email){
        boolean ifEXist=userExist(email);
        if(ifEXist && userIsActive(email)==false){
            User user = userRepository.findByEmail(email);
            if(customerRepository.existsById(user.getId())) {
                ConfirmationToken oldtoken = tokenRepository.findByUserEmail(user.getEmail());
                tokenRepository.delete(oldtoken);
                ConfirmationToken token = new ConfirmationToken(user);
                tokenRepository.save(token);
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(user.getEmail());
                mailMessage.setSubject("Activation Link!");
                mailMessage.setText("To confirm your account, please click here : "
                        + "http://localhost:8080/confirm-account?token=" + token.getConfirmationToken());
                emailService.sendEmail(mailMessage);
            }
            return user;
        }
        else {
            return null;
        }
    }
}
