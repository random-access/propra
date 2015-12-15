package ess.exc;

/**
 * This Exception is thrown when a problem occurs in the context of the property
 * file, this means normally a typo in a value or a key, or a missing key or
 * value.
 * 
 * @author Monika Schrenk
 *
 */
public class PropertyException extends Exception {

    private static final long serialVersionUID = -2784930798602311808L;

    /**
     * Constructs a new PropertyException, passing an error message.
     * 
     * @param message an error message holding additional information.
     */
    public PropertyException(String message) {
        super(message);
    }
    
    // TODO evaluate if constructors below are necessary

    /**
     * Instantiates a new PropertyException, passing its cause.
     *
     * @param cause a Throwable that caused this exception.
     */
    public PropertyException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new PropertyException, passing an error message
     * and its cause.
     * 
     * @param message an error message holding additional information.
     * @param cause  a Throwable that caused this exception.
     */
    public PropertyException(String message, Throwable cause) {
        super(message, cause);
    }

}
