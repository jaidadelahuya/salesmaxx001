package com.salesmaxx.entities.exception;

public class RollbackFailureException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2282016296333666091L;
	public RollbackFailureException(String message, Throwable cause) {
        super(message, cause);
    }
    public RollbackFailureException(String message) {
        super(message);
    }
}
