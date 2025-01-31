package com.capitole.pricinginditex.application.service;

import com.capitole.pricinginditex.application.dto.PriceResponse;
import com.capitole.pricinginditex.domain.exception.PriceNotFoundException;
import com.capitole.pricinginditex.domain.model.Price;
import com.capitole.pricinginditex.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PriceService {
    private final PriceRepository priceRepository;

    public PriceResponse getApplicablePrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {


        Price price = priceRepository.findApplicablePrice(productId, brandId, applicationDate)
                .orElseThrow(() -> new PriceNotFoundException(productId, brandId, applicationDate));
        return new PriceResponse(price.getProductId(), price.getBrandId(), price.getPriceList(), price.getStartDate(), price.getEndDate(), price.getPrice());
    }
}