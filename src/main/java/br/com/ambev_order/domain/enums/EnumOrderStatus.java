package br.com.ambev_order.domain.enums;

public enum EnumOrderStatus {
	
	RECEBIDO("Recebido"),
    PROCESSANDO("Processando"),
    PROCESSADO("Processado"),
    ENVIADO("Enviado"),
    ERRO("Erro");
	
	private String descricao;
	
	private EnumOrderStatus(String descricao) {
        this.descricao = descricao;
    }

	public String getDescricao() {
		return descricao;
	}
	
}
