package ess.io.exc;

/**
 * This <code>Exception</code> is thrown when a problem occurs during XML input or output.
 */
public class DataExchangeException extends Exception {

    private static final long serialVersionUID = -951095821705704361L;

    /**
     * Instantiates a new <code>DataExchangeException</code>, passing a message.
     *
     * @param message
     *            an error message holding additional information.
     */
    public DataExchangeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new <code>DataExchangeException</code>, passing its cause.
     *
     * @param cause
     *            a Throwable that caused this exception.
     */
    public DataExchangeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new <code>DataExchangeException</code>, passing an error message and its
     * cause.
     * 
     * @param message
     *            an error message holding additional information.
     * @param cause
     *            a Throwable that caused this exception.
     */
    public DataExchangeException(String message, Throwable cause) {
        super(message, cause);
    }
}
