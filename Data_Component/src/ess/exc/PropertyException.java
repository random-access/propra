package ess.exc;

/**
 * This exception is thrown when a problem occurs in the context of the property
 * file, this means normally a typo in a value or a key, or a missing key or
 * value.
 * 
 * @author Monika Schrenk
 *
 */
public class PropertyException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new PropertyException with the given error message.
     * 
     * @param message an error message.
     */
    public PropertyException(String message) {
        super(message);
    }

    /**
     * Constructs a new PropertyException with the given error message
     * and the cause of this exception as a Throwable
     * 
     * @param message an error message.
     * @param cause a Throwable causing this exception to be thrown
     */
    public PropertyException(String message, Throwable cause) {
        super(message, cause);
    }

}
