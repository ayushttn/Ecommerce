package com.ecommerce.Service;
import com.ecommerce.Entities.Customer;
import com.ecommerce.Entities.Seller;
import com.ecommerce.Entities.User;
import com.ecommerce.Exceptions.UserNotFoundException;
import com.ecommerce.Repos.CustomerRepository;
import com.ecommerce.Repos.SellerRepository;
import com.ecommerce.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;

@Service
public class AdminService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public List<Object []> viewAllCustomers(){
        List<Object[]> partialData =customerRepository.findAllCustomer(PageRequest.of(0,10, Sort.by("id")));
        return partialData;
    }

    public List<Object []> viewAllSeller(){
        List<Object[]> partialData =sellerRepository.findAllSeller(PageRequest.of(0,10, Sort.by("id")));
        return partialData;
    }

    public User activateUser(User user) {
        user.setActive(true);
        userRepository.save(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Account Activation!");
        mailMessage.setText("Congratulations!!\nYour account is being activated. We hope you enjoy our service");
        emailService.sendEmail(mailMessage);
        return user;
    }

    public User deactivateUser(User user) {
        user.setActive(false);
        userRepository.save(user);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Account De-Activation!");
        mailMessage.setText("Your account is De-Activated :( \nFeel free to connect if you want to activate you account.");
        emailService.sendEmail(mailMessage);
        return user;
    }
}
