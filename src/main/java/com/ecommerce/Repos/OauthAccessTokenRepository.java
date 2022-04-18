package com.ecommerce.Repos;

import com.ecommerce.Entities.OauthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Blob;

public interface OauthAccessTokenRepository extends JpaRepository<OauthAccessToken, String> {

    @Query("select t.userName from OauthAccessToken t where t.token=:token")
    public String findByTokenId(@Param("token") String token);
}
