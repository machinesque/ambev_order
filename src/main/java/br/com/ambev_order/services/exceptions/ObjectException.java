package br.com.ambev_order.services.exceptions;

public class ObjectException extends BusinessException {

	private static final long serialVersionUID = 1L;

    public ObjectException(String message) {
       super(message);
    }

    public ObjectException(String message, Throwable cause) {
       super(message, cause);
    }
	
}
