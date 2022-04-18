package com.ecommerce.Service;

import com.ecommerce.Entities.Address;
import com.ecommerce.Entities.Customer;
import com.ecommerce.Entities.User;
import com.ecommerce.Exceptions.UserNotFoundException;
import com.ecommerce.Repos.AddressRepository;
import com.ecommerce.Repos.CustomerRepository;
import com.ecommerce.Repos.UserRepository;
import com.ecommerce.dto.AddressDto;
import com.ecommerce.dto.CustomerDto;
import com.ecommerce.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AddressRepository addressRepository;

    Boolean customerExist(String email){
        return userRepository.findByEmail(email) !=null ? true : false;
    }

    public List<Object []> getMyData(String email){
        if (customerExist(email)){
            List<Object[]> partialData = customerRepository.findDataById(email);
            return partialData;
        }
        else {
            throw new UserNotFoundException("Customer not found");
        }
    }

    public List<Object []> getAddress(String email){
        if (customerExist(email)){
            List<Object[]> partialData = customerRepository.findAddress(email);
            return partialData;
        }
        else {
            throw new UserNotFoundException("Customer not found");
        }
    }


    public void updateProfile(String email, CustomerDto customerDto){
        if (customerExist(email)){
            User user=userRepository.findByEmail(email);
            user.setFirstName(customerDto.getFirstName());
            user.setLastName(customerDto.getLastName());
            Long id = user.getId();
            Customer customer = customerRepository.findCustomerById(id);
            customer.setContact(customerDto.getContact());
            userRepository.save(user);
            customerRepository.save(customer);
        }
        else {
            throw new UserNotFoundException("Customer not found");
        }
    }

    public void updateMyPassword(String email, CustomerDto customerDto){
        if (customerExist(email)){
            User user=userRepository.findByEmail(email);
            user.setPassword(passwordEncoder.encode(customerDto.getPassword()));
            userRepository.save(user);
        }
        else {
            throw new UserNotFoundException("Customer not found");
        }
    }

    public void addAddress(String email, AddressDto addressDto){
        if (customerExist(email)){
            User user=userRepository.findByEmail(email);
            Address address = new Address();
            address.setCity(addressDto.getCity());
            address.setState(addressDto.getState());
            address.setCountry(addressDto.getCountry());
            address.setAddressLine(addressDto.getAddressLine());
            address.setZipCode(addressDto.getZipCode());
            address.setLabel(addressDto.getLabel());
            address.setUser(user);
            addressRepository.save(address);
        }
        else {
            throw new UserNotFoundException("Customer not found");
        }
    }

    public void deleteAddress(String email, Long id){
        if (customerExist(email)){
            User user=userRepository.findByEmail(email);
            addressRepository.delete(addressRepository.getByIdAndUser(id, user));
        }
        else {
            throw new UserNotFoundException("Address not found");
        }
    }

    public void updateAddress(String email, AddressDto addressDto, Long id){
        if (customerExist(email)){
            User user=userRepository.findByEmail(email);
            Address address = addressRepository.getByIdAndUser(id, user);
            address.setCity(addressDto.getCity());
            address.setState(addressDto.getState());
            address.setCountry(addressDto.getCountry());
            address.setAddressLine(addressDto.getAddressLine());
            address.setZipCode(addressDto.getZipCode());
            address.setLabel(addressDto.getLabel());
            addressRepository.save(address);
        }
        else {
            throw new UserNotFoundException("Address not found");
        }
    }
}
