package br.com.ambev_order.kafka.messaging;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.ambev_order.domain.dtos.OrderEventoProcessadoDTO;
import br.com.ambev_order.domain.enums.EnumOrderStatus;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderEventPublisher {

	private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(OrderEventoProcessadoDTO orderEvento) {

        kafkaTemplate.send(
            EnumOrderStatus.PROCESSADO.getDescricao(),
            orderEvento.id().toString(),
            orderEvento
        );
    }
	
}
