package org.bank.exception;

public class CustomerInvalidDataException extends RuntimeException {
	private String exceptionMsg;

	public CustomerInvalidDataException(String exceptionMsg) {
		super();
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	@Override
	public String toString() {
		return "CustomerInvalidDataException [exceptionMsg=" + exceptionMsg + "]";
	}
	
	
}
