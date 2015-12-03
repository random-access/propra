package ess.exc;

/**
 * This class can be used for throwing exceptions if a length value in 
 * external data cannot be converted into an internal value because it is
 * invalid.
 * 
 * @author Monika Schrenk
 *
 */
public class InvalidLengthValueException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new InvalidLengthValueException.
     *
     * @param message the message
     */
    public InvalidLengthValueException(String message) {
        super(message);
    }

    /**
     * Instantiates a new InvalidLengthValueException.
     *
     * @param cause the cause
     */
    public InvalidLengthValueException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new InvalidLengthValueException.
     *
     * @param message the message
     * @param cause the cause
     */
    public InvalidLengthValueException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new InvalidLengthValueException.
     *
     * @param message the message
     * @param cause the cause
     * @param enableSuppression the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public InvalidLengthValueException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
