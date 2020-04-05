package com.awbd.restaurantreview.repositories;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.awbd.restaurantreview.domain.RefreshToken;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID> {
    @Query("SELECT rt FROM RefreshToken rt WHERE rt.token = :refreshToken")
    Optional<RefreshToken> findByToken(@Param("refreshToken") String refreshToken);
}
