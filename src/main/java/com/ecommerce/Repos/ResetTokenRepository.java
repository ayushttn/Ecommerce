package com.ecommerce.Repos;

import com.ecommerce.Entities.ConfirmationToken;
import com.ecommerce.Entities.ResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Long> {

    ResetToken findByResetToken(String resetToken);

    @Query("from ResetToken where userEntity.email =:s")
    ResetToken findByUserEmail(@Param("s")String s);
}
