package com.capitole.pricinginditex.infraestructure.adapter.persistence;

import com.capitole.pricinginditex.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SpringDataPriceRepository extends JpaRepository<Price, Long> {
    @Query("""
                SELECT p FROM Price p 
                WHERE p.productId = :productId 
                AND p.brandId = :brandId 
                AND :applicationDate BETWEEN p.startDate AND p.endDate
                ORDER BY p.priority DESC
                LIMIT 1
            """)
    Optional<Price> findApplicablePrice(
            @Param("productId") Integer productId,
            @Param("brandId") Integer brandId,
            @Param("applicationDate") LocalDateTime applicationDate);
}
