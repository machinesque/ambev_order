package br.com.ambev_order.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ambev_order.controller.OrderController;
import br.com.ambev_order.domain.Order;
import br.com.ambev_order.domain.dtos.OrderEventoProcessadoDTO;
import br.com.ambev_order.domain.dtos.OrderRequestDTO;
import br.com.ambev_order.domain.dtos.OrderResponseDTO;
import br.com.ambev_order.domain.enums.EnumOrderStatus;
import br.com.ambev_order.domain.mappers.OrderMapper;
import br.com.ambev_order.kafka.messaging.OrderEventPublisher;
import br.com.ambev_order.repositories.OrderRepository;
import br.com.ambev_order.services.interfaces.OrderService;
import br.com.ambev_order.services.exceptions.ObjectException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private final OrderRepository orderRepository;
	
	private final OrderMapper orderMapper;
    private final OrderController orderController;
    private final OrderEventPublisher eventPublisher;

	public Order salvar(Order order) {
		
		return orderRepository.save(order);
		
	}

	@Override
	public OrderResponseDTO create(OrderRequestDTO newOrder) {
		
		orderController.validarOrderDuplicada(newOrder.idPedidoExterno(), orderRepository.existsByIdPedidoExterno(newOrder.idPedidoExterno()));

        Order order = orderController.montarOrder(newOrder);

        BigDecimal valorTotal = orderController.calcularPrecoTotal(order.getItems());

        order.setValorTotal(valorTotal);
        order.setStatus(EnumOrderStatus.PROCESSADO);

        Order orderSalva = orderRepository.save(order);

        eventPublisher.publish(
                new OrderEventoProcessadoDTO(
                		orderSalva.getId(),
                		orderSalva.getValorTotal()
                )
        );

        return orderMapper.toResponse(orderSalva);
		
	}

	@Override
	@Transactional(readOnly = true)
	public OrderResponseDTO findById(UUID id) {
		
		Order order = orderRepository.findByIdWithItems(id)
                .orElseThrow(() -> new ObjectException("Order não encontrada!"));

        return orderMapper.toResponse(order);
        
	}

	@Override
	@Transactional(readOnly = true)
	public OrderResponseDTO findByExternalOrderId(String idPedidoExterno) {
		
		Order order = orderRepository.findByIdPedidoExterno(idPedidoExterno)
                .orElseThrow(() -> new ObjectException("Order não encontrada!"));

        return orderMapper.toResponse(order);
		
	}

	@Override
	public Page<OrderResponseDTO> findAll(
			@PageableDefault(page = 0, size = 20, sort = "dataCriacao", direction = Sort.Direction.DESC) 
			Pageable paginacaoOrder) {
		
		return orderRepository.findAll(paginacaoOrder).map(orderMapper::toResponse);
		
	}
	
}
