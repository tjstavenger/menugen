/**
 * 
 */
package com.googlecode.menugen.exception;

/**
 * Checked exception super class for MenuGen application
 */
public class MenuGenException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * New exception without a message.
	 */
	public MenuGenException() {
		super();
	}

	/**
	 * New exception with a message.
	 * 
	 * @param message
	 *            String exception message
	 */
	public MenuGenException(String message) {
		super(message);
	}

	/**
	 * New chained exception without a message.
	 * 
	 * @param throwable
	 *            Throwable to chain
	 */
	public MenuGenException(Throwable throwable) {
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
	public MenuGenException(String message, Throwable throwable) {
		super(message, throwable);
	}
}