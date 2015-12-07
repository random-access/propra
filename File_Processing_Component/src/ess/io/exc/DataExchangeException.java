package ess.io.exc;

/**
 * This Exception is thrown when a problem occurs during XML input or output.
 */
public class DataExchangeException extends Exception {

    private static final long serialVersionUID = -951095821705704361L;

    /**
     * Instantiates a new DataExchangeException, passing a message.
     *
     * @param message
     *            an error message holding additional information.
     */
    public DataExchangeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new DataExchangeException, passing its cause.
     *
     * @param cause
     *            a Throwable that caused this exception.
     */
    public DataExchangeException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new DataExchangeException, passing an error message and its
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
