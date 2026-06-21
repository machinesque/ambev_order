package br.com.ambev_order.kafka.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import br.com.ambev_order.domain.dtos.OrderEventoProcessadoDTO;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class OrderConsumer {

	@KafkaListener(
        topics = "order-processado",
        groupId = "order-grupo"
    )
    public void consume(OrderEventoProcessadoDTO event) {
        log.info("Pedido recebido {}", event);
    }
	
}
