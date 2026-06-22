package br.com.ambev_order.domain;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_itens")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
	
	@Column(nullable = false, length = 50)
	private String codigoProduto;

	@Column(nullable = false, length = 255)
    private String produto;

	@Column(nullable = false)
    private Integer quantidade;

	@Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal precoUnitario;

	@Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal precoTotal;

    @ManyToOne
    @JoinColumn(name = "id_order", nullable = false)
    private Order order;
	
}
