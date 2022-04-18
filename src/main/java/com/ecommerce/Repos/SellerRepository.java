package com.ecommerce.Repos;

import com.ecommerce.Entities.Seller;
import com.ecommerce.Entities.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    Seller findSellerById(Long id);

    @Query("select u.id, u.email, concat(u.firstName,' ', u.lastName), u.isActive, s.companyName, s.companyContact, concat(a.addressLine , ' ', a.city, ' ', a.state, ' ', a.zipCode, ' ', a.label) from User u, Seller s join Address a on a.user=s.user where s.id = u.id")
    List<Object[]> findAllSeller(Pageable pageable);

    @Query("select u.id, u.firstName, u.lastName, u.isActive, s.companyName, s.companyContact, s.gst, a.city, a.state, a.country, a.addressLine, a.zipCode from User u, Seller s join Address a on a.user=s.user where u.email=:s and s.id=u.id")
    List<Object []> findDataById(@Param("s") String s);


}
