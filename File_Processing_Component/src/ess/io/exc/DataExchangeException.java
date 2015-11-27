package ess.io.exc;

// TODO: Auto-generated Javadoc
/**
 * The Class DataExchangeException.
 */
public class DataExchangeException extends Exception {
	
	private static final long serialVersionUID = -951095821705704361L;

	/**
	 * Instantiates a new data exchange exception.
	 *
	 * @param message the message
	 */
	public DataExchangeException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new data exchange exception.
	 *
	 * @param cause the cause
	 */
	public DataExchangeException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new data exchange exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DataExchangeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new data exchange exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public DataExchangeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
