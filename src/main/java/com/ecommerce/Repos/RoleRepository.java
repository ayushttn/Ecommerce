package com.ecommerce.Repos;

import com.ecommerce.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String s);
}
