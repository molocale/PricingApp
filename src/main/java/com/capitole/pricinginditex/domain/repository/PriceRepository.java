package com.capitole.pricinginditex.domain.repository;

import com.capitole.pricinginditex.domain.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<Price> findApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate);
}