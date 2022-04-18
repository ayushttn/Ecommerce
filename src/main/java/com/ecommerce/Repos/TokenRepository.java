package com.ecommerce.Repos;

import com.ecommerce.Entities.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepository extends JpaRepository<ConfirmationToken,Long> {

    ConfirmationToken findByConfirmationToken(String confirmationToken);

    @Query("from ConfirmationToken where userEntity.email =:s")
    ConfirmationToken findByUserEmail(@Param("s")String s);
}
