package br.com.ambev_order.domain;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	
	private String codigoProduto;

    private String produto;

    private Integer quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal precoTotal;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
	
}
