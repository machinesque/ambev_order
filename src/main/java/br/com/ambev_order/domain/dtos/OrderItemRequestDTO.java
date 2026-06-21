package br.com.ambev_order.domain.dtos;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record OrderItemRequestDTO(
		
		@NotBlank
        String codigoProduto,

        @NotBlank
        String produto,

        @Positive
        Integer quantidade,

        @Positive
        BigDecimal precoUnitario
		
		) {

}
