package com.capitole.pricinginditex.domain.exception;

import java.time.LocalDateTime;

public class PriceNotFoundException extends RuntimeException {
    public PriceNotFoundException(Integer productId, Integer brandId, LocalDateTime applicationDate) {
        super(String.format("No price found for product %d, brand %d on date %s",
                productId, brandId, applicationDate));
    }
}