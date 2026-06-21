package br.com.ambev_order.domain.mappers;

import org.springframework.stereotype.Component;

import br.com.ambev_order.domain.Order;
import br.com.ambev_order.domain.OrderItem;
import br.com.ambev_order.domain.dtos.OrderItemResponseDTO;
import br.com.ambev_order.domain.dtos.OrderResponseDTO;

@Component
public class OrderMapper {

	public OrderResponseDTO toResponse(Order order) {

        return new OrderResponseDTO(
                order.getId(),
                order.getIdPedidoExterno(),
                order.getStatus(),
                order.getValorTotal(),
                order.getDataCriacao(),
                order.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .toList()
        );
    }

    private OrderItemResponseDTO toItemResponse(
            OrderItem item) {

        return new OrderItemResponseDTO(
                item.getCodigoProduto(),
                item.getProduto(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getPrecoTotal()
        );
    }
	
}
