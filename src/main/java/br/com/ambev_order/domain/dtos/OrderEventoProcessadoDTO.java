package br.com.ambev_order.domain.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderEventoProcessadoDTO(
		
		UUID id,
        BigDecimal valorTotal
		
		) {

}
