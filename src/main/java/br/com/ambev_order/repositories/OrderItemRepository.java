package br.com.ambev_order.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ambev_order.domain.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {

	
	
}
