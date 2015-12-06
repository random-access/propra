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
     * Instantiates a new InvalidLengthValueException, passing an error message.
     *
     * @param message an error message with additional information.
     */
    public InvalidLengthValueException(String message) {
        super(message);
    }

    /**
     * Instantiates a new InvalidLengthValueException, passing its cause.
     *
     * @param cause a Throwable that caused this exception.
     */
    public InvalidLengthValueException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new InvalidLengthValueException, passing an error message
     * and its cause.
     *
     * @param message an error message with additional information.
     * @param cause a Throwable that caused this exception.
     */
    public InvalidLengthValueException(String message, Throwable cause) {
        super(message, cause);
    }

}
