package org.bank.exception;

public class CustomerInvalidDataException extends RuntimeException {
	
	public CustomerInvalidDataException(String msg) {
		super(msg);
	}

}
