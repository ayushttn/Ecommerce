package com.ecommerce.Repos;

import com.ecommerce.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String s);

    @Query(nativeQuery = true, value = "select is_active from User u where u.email=:s")
    Boolean findByActive(@Param("s") String s);
}
