package com.ecommerce.Repos;

import com.ecommerce.Entities.Address;
import com.ecommerce.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address getByIdAndUser(Long id, User user);
}
