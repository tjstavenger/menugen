/**
 * 
 */
package com.googlecode.menugen.exception;

/**
 * Unchecked exception super class for MenuGen application.
 */
public class MenuGenRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * New exception without a message.
	 */
	public MenuGenRuntimeException() {
		super();
	}

	/**
	 * New exception with a message.
	 * 
	 * @param message
	 *            String exception message
	 */
	public MenuGenRuntimeException(String message) {
		super(message);
	}

	/**
	 * New chained exception without a message.
	 * 
	 * @param throwable
	 *            Throwable to chain
	 */
	public MenuGenRuntimeException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * New chained exception with a message.
	 * 
	 * @param message
	 *            String exception message
	 * @param throwable
	 *            Throwable to chain
	 */
	public MenuGenRuntimeException(String message, Throwable throwable) {
		super(message, throwable);
	}
}