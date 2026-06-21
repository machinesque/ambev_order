package br.com.ambev_order.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ambev_order.domain.dtos.OrderRequestDTO;
import br.com.ambev_order.domain.dtos.OrderResponseDTO;
import br.com.ambev_order.services.interfaces.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderResource {
	
	@Autowired
	private final OrderService orderService;

	@PostMapping
	public ResponseEntity<OrderResponseDTO> create(@Valid @RequestBody OrderRequestDTO newOrder) {

		OrderResponseDTO response = orderService.create(newOrder);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
	
	
	
//	@PostMapping
//	public ResponseEntity<OrderResponseDTO> criarOrder(@Valid @RequestBody OrderRequestDTO novaOrder) {
//		
//		Order order = new Order();
//		
//		orderService
//		
//		order.setDataCriacao(novaOrder.getDataCriacao());
//		order.setIdPedidoExterno(novaOrder.getIdPedidoExterno());
//		order.setStatus(novaOrder.getStatus());
//		order.setValorTotal(novaOrder.getValorTotal());
//		
//		return ResponseEntity.status(HttpStatus.CREATED).body(response);
//		
//	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<OrderResponseDTO> findById(@PathVariable UUID id) {
		
		return ResponseEntity.ok(orderService.findById(id));
		
	}
	
	@GetMapping(value = "/external/{idPedidoExterno}")
	public ResponseEntity<OrderResponseDTO> findByIdPedidoExterno(@PathVariable String idPedidoExterno) {
		
		return ResponseEntity.ok(orderService.findByExternalOrderId(idPedidoExterno));
		
	}
	
}
