package br.com.ambev_order.domain.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDTO(
		
		String codigoProduto,

        String produto,

        Integer quantidade,

        BigDecimal precoUnitario,

        BigDecimal precoTotal
		
		) {

}
