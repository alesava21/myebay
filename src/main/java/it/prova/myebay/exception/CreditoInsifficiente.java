package it.prova.myebay.exception;

public class CreditoInsifficiente extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public CreditoInsifficiente() {}
	
	public CreditoInsifficiente (String message) {
		super(message);
	}
	

}
