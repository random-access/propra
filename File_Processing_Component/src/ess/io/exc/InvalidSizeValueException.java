package ess.io.exc;

/**
 * This Exception is thrown when the measurements of tiles and composite read
 * from XML are invalid.
 */
public class InvalidSizeValueException extends Exception {

    private static final long serialVersionUID = 6514622957651060030L;

    /**
     * Instantiates a new invalid tile size exception, passing a message.
     *
     * @param message
     *            an error message holding additional information.
     */
    public InvalidSizeValueException(String message) {
        super(message);
    }

    /**
     * Instantiates a new invalid tile size exception, passing its cause.
     *
     * @param cause
     *            a Throwable that caused this exception
     */
    public InvalidSizeValueException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new InvalidSizeValueException, passing an error message and its
     * cause.
     * 
     * @param message
     *            an error message holding additional information.
     * @param cause
     *            a Throwable that caused this exception.
     */
    public InvalidSizeValueException(String message, Throwable cause) {
        super(message, cause);
    }

}
