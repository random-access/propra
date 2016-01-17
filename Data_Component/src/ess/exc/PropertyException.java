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
     * Constructs a new <code>PropertyException</code>, passing an error message.
     * 
     * @param message an error message holding additional information.
     */
    public PropertyException(String message) {
        super(message);
    }

}
