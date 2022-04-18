package com.ecommerce.Repos;

import com.ecommerce.Entities.Customer;
import com.ecommerce.Entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findById(Customer customer);

    Customer findCustomerById(Long id);

    @Query("select u.id, concat(u.firstName,' ', u.lastName) , u.email, u.isActive from User u join Customer c on c.id = u.id")
    List<Object[]> findAllCustomer(Pageable pageable);

    @Query("select u.id, u.firstName, u.lastName, u.isActive, c.contact from User u join Customer c on c.id=u.id")
    List<Object []> findDataById(@Param("s") String s);

    @Query("select a.city, a.state, a.country, a.addressLine, a.label, a.zipCode from Address a join User u on u.customer=a.user where u.email=:s")
    List<Object []> findAddress(@Param("s") String s);
}
