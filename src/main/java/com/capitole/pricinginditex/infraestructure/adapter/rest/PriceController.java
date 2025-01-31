package com.capitole.pricinginditex.infraestructure.adapter.rest;

import com.capitole.pricinginditex.application.dto.PriceResponse;
import com.capitole.pricinginditex.application.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
@Tag(name = "Pricing API", description = "API para consultar precios")
public class PriceController {
    private final PriceService priceService;

    @GetMapping
    @Operation(summary = "Obtener precio aplicable", description = "Devuelve el precio de un producto según la fecha, producto y marca.")
    public ResponseEntity<PriceResponse> getPrice(
            @RequestParam @Parameter(description = "Fecha de aplicación en formato yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date,
            @RequestParam @Parameter(description = "ID del producto") Integer productId,
            @RequestParam @Parameter(description = "ID de la cadena") Integer brandId) {

        PriceResponse response = priceService.getApplicablePrice(productId, brandId, date);
        return ResponseEntity.ok(response);
    }
}
