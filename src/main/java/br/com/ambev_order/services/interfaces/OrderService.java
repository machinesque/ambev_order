package br.com.ambev_order.services.interfaces;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ambev_order.domain.dtos.OrderRequestDTO;
import br.com.ambev_order.domain.dtos.OrderResponseDTO;

public interface OrderService {
	
	OrderResponseDTO create(OrderRequestDTO newOrder);

	OrderResponseDTO findById(UUID id);

	OrderResponseDTO findByExternalOrderId(String idPedidoExterno);

    Page<OrderResponseDTO> findAll(Pageable paginacaoOrder);
	
}
