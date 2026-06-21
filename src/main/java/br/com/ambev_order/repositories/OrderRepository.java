package br.com.ambev_order.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ambev_order.domain.Order;
import br.com.ambev_order.domain.enums.EnumOrderStatus;
import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

	Optional<Order> findByIdPedidoExterno(String idPedidoExterno);

    boolean existsByIdPedidoExterno(String IdPedidoExterno);

    Page<Order> findAllByStatus(EnumOrderStatus status, Pageable pageable);
    
    @Query("""
            select distinct o
            from Order o
            left join fetch o.items
            where o.id = :id
            """)
     Optional<Order> findByIdWithItems(UUID id);
	
}
