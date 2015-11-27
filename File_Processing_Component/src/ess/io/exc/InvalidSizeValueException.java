package ess.io.exc;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidTileSizeException.
 */
public class InvalidSizeValueException extends Exception {

	private static final long serialVersionUID = 6514622957651060030L;

	/**
	 * Instantiates a new invalid tile size exception.
	 *
	 * @param message the message
	 */
	public InvalidSizeValueException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new invalid tile size exception.
	 *
	 * @param cause the cause
	 */
	public InvalidSizeValueException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new invalid tile size exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public InvalidSizeValueException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new invalid tile size exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public InvalidSizeValueException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
