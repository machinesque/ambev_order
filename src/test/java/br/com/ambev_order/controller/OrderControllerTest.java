package br.com.ambev_order.controller;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.ambev_order.domain.OrderItem;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	private OrderController orderController;
	
	@BeforeEach
    void setup() {
		orderController = new OrderController();
    }
	
	@Test
    void deveCalcularOrderPrecoTotal() {

        OrderItem item1 = OrderItem.builder()
                .precoTotal(new BigDecimal("100.00"))
                .build();

        OrderItem item2 = OrderItem.builder()
                .precoTotal(new BigDecimal("50.00"))
                .build();

        BigDecimal total = orderController.calcularOrderValorTotal(List.of(item1, item2));

        assertEquals(
                new BigDecimal("150.00"),
                total
        );
    }

}
