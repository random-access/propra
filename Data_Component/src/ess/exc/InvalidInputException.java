package ess.exc;

/**
 * This class can be used for throwing exceptions if an input parameter
 * that was entered by the user is invalid, in wrong order, or if the number of 
 * parameters is not exactly as expected.
 * 
 * @author Monika Schrenk
 *
 */
public class InvalidInputException extends Exception {

    private static final long serialVersionUID = -2594576840512710633L;

    /**
     * Instantiates a new InvalidInputException, passing an error message.
     *
     * @param message an error message with additional information.
     */
	public InvalidInputException(String message) {
		super(message);
	}

    /**
     * Instantiates a new InvalidInputException, passing its cause.
     *
     * @param cause a Throwable that caused this exception.
     */
	public InvalidInputException(Throwable cause) {
		super(cause);
	}

    /**
     * Instantiates a new InvalidInputException, passing an error message
     * and its cause.
     *
     * @param message an error message with additional information.
     * @param cause a Throwable that caused this exception.
     */
	public InvalidInputException(String message, Throwable cause) {
		super(message, cause);
	}
}
