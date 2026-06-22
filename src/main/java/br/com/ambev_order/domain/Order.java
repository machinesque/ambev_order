package br.com.ambev_order.domain;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.ambev_order.domain.enums.EnumOrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Index;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (
		name = "orders",
	    uniqueConstraints = {
	        @UniqueConstraint(
	            columnNames = "id_pedido_externo"
	        )
	    }, indexes = { 
	    		@Index( 
	    				name = "idx_pedido_externo", 
	    				columnList = "pedido_externo" 
	    		), 
	    		@Index( 
	    				name = "idx_order_status", 
	    				columnList = "status" 
	    		), 
	    		@Index( 
	    				name = "idx_data_criacao", 
	    				columnList = "data_criacao" 
	    		) 
	    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id; 
	
	@Column(name = "id_pedido_externo", nullable = false, length = 100)
	private String idPedidoExterno;

	@Column(name = "valor_total", nullable = false, precision = 19, scale = 2)
    private BigDecimal valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumOrderStatus status;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private OffsetDateTime dataCriacao;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItem> items = new ArrayList<>();
    
}
