package br.com.ambev_order.domain.dtos;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record OrderRequestDTO(
		
		@NotBlank
        String idPedidoExterno,

        @NotEmpty
        List<OrderItemRequestDTO> items
		
		) {

}
