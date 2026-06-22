CREATE TABLE orders
(
    id UUID PRIMARY KEY,

    id_pedido_externo VARCHAR(100) NOT NULL,

    status VARCHAR(30) NOT NULL,

    valor_total NUMERIC(19,2) NOT NULL,

    data_criacao TIMESTAMP NOT NULL,

    updated_at TIMESTAMP
);

ALTER TABLE orders
ADD CONSTRAINT fk_id_pedido_externo
UNIQUE (id_pedido_externo);

CREATE INDEX idx_pedido_externo
ON orders(id_pedido_externo);

CREATE INDEX idx_order_status
ON orders(status);

CREATE INDEX idx_data_criacao
ON orders(data_criacao);


CREATE TABLE order_itens
(
    id UUID PRIMARY KEY,

    id_order UUID NOT NULL,

    codigoProduto VARCHAR(50) NOT NULL,

    produto VARCHAR(255) NOT NULL,

    quantidade INTEGER NOT NULL,

    preco_unitario NUMERIC(19,2) NOT NULL,

    preco_total NUMERIC(19,2) NOT NULL,

    CONSTRAINT fk_order_item_order
        FOREIGN KEY(id_order)
        REFERENCES orders(id)
);
