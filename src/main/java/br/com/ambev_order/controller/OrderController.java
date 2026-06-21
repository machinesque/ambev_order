package br.com.ambev_order.controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.ambev_order.domain.Order;
import br.com.ambev_order.domain.OrderItem;
import br.com.ambev_order.domain.dtos.OrderRequestDTO;
import br.com.ambev_order.domain.enums.EnumOrderStatus;
import br.com.ambev_order.repositories.OrderRepository;
import br.com.ambev_order.services.exceptions.ObjectException;

@Service
public class OrderController {

	public BigDecimal calcularPrecoTotal(List<OrderItem> items) {

        return items.stream()
                .map(item ->
                     item.getPrecoUnitario()
                         .multiply(
                             BigDecimal.valueOf(
                                 item.getQuantidade()
                             )
                         )
                ).reduce(BigDecimal.ZERO, BigDecimal::add);
                
    }
	
	public Order montarOrder(OrderRequestDTO newOrder) {

        Order order = new Order();

        order.setIdPedidoExterno(newOrder.idPedidoExterno());

        order.setStatus(EnumOrderStatus.RECEBIDO);

        order.setDataCriacao(OffsetDateTime.now());

        List<OrderItem> items =
        		newOrder.items()
                        .stream()
                        .map(itemOrder -> {

                            OrderItem item = new OrderItem();

                            item.setCodigoProduto(itemOrder.codigoProduto());

                            item.setProduto(itemOrder.produto());

                            item.setQuantidade(itemOrder.quantidade());

                            item.setPrecoUnitario(itemOrder.precoUnitario());

                            item.setPrecoTotal(itemOrder.precoUnitario().multiply(BigDecimal.valueOf(itemOrder.quantidade())));

                            item.setOrder(order);

                            return item;

                        })
                        .toList();

        order.setItems(items);

        return order;
    }
	
	public void validarOrderDuplicada(String idPedidoExterno, Boolean orderExistsByIdPedidoExterno) {

        if (orderExistsByIdPedidoExterno) {
            throw new ObjectException("Order Duplicada");
        }
    }
	
}
