package br.com.ambev_order.domain.dtos;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import br.com.ambev_order.domain.enums.EnumOrderStatus;

public record OrderResponseDTO(
		
		UUID id,

        String idPedidoExterno,

        EnumOrderStatus status,

        BigDecimal valorTotal,

        OffsetDateTime dataCriacao,

        List<OrderItemResponseDTO> items
		
		) {

}
