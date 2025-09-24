package net.abderrahimself.accounts.service.client;

import net.abderrahimself.accounts.dto.CardsDto;
import net.abderrahimself.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient{

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        // Return empty/default response instead of null
        return ResponseEntity.ok(null); // Or return a default CardsDto
    }
}
