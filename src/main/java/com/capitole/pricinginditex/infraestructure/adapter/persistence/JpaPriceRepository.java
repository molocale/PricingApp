package com.capitole.pricinginditex.infraestructure.adapter.persistence;

import com.capitole.pricinginditex.domain.model.Price;
import com.capitole.pricinginditex.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaPriceRepository implements PriceRepository {

    private final SpringDataPriceRepository repository;

    @Override
    public Optional<Price> findApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        return repository.findApplicablePrice(
                productId, brandId, applicationDate);
    }
}