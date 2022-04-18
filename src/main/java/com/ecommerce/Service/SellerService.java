package com.ecommerce.Service;

import com.ecommerce.Entities.Address;
import com.ecommerce.Entities.Customer;
import com.ecommerce.Entities.Seller;
import com.ecommerce.Entities.User;
import com.ecommerce.Exceptions.UserNotFoundException;
import com.ecommerce.Repos.AddressRepository;
import com.ecommerce.Repos.SellerRepository;
import com.ecommerce.Repos.UserRepository;
import com.ecommerce.dto.AddressDto;
import com.ecommerce.dto.CustomerDto;
import com.ecommerce.dto.SellerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AddressRepository addressRepository;

    Boolean sellerExist(String email){
        return userRepository.findByEmail(email) !=null ? true : false;
    }

    public List<Object []> getMyData(String email){
        if (sellerExist(email)){
            List<Object[]> partialData = sellerRepository.findDataById(email);
            return partialData;
        }
        else {
            throw new UserNotFoundException("Seller not found");
        }
    }

    public void updateProfile(String email, SellerDto sellerDto){
        if (sellerExist(email)){
            User user=userRepository.findByEmail(email);
            user.setFirstName(sellerDto.getFirstName());
            user.setLastName(sellerDto.getLastName());
            Long id = user.getId();
            Seller seller = sellerRepository.findSellerById(id);
            seller.setCompanyContact(sellerDto.getCompanyContact());
            seller.setCompanyName(seller.getCompanyName());
            userRepository.save(user);
            sellerRepository.save(seller);
        }
        else {
            throw new UserNotFoundException("Seller not found");
        }
    }

    public void updateMyPassword(String email, SellerDto sellerDto){
        if (sellerExist(email)){
            User user=userRepository.findByEmail(email);
            user.setPassword(passwordEncoder.encode(sellerDto.getPassword()));
            userRepository.save(user);
        }
        else {
            throw new UserNotFoundException("Seller not found");
        }
    }

    public void updateAddress(String email, AddressDto addressDto, Long id){
        if (sellerExist(email)){
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
