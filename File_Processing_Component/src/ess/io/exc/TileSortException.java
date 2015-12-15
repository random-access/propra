package ess.io.exc;

/**
 * This Exception is thrown when duplicates of already existing tile IDs are
 * discovered.
 */
public class TileSortException extends Exception {

    private static final long serialVersionUID = 7632309294138737085L;

    /**
     * Instantiates a new DoubleTileSortException, passing an error message.
     *
     * @param message
     *            an error message holding additional information.
     */
    public TileSortException(String message) {
        super(message);
    }

    /**
     * Instantiates a new DoubleTileSortException, passing its cause.
     *
     * @param cause
     *            a Throwable that caused this exception.
     */
    public TileSortException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new DoubleTileSortException, passing an error message and its
     * cause.
     * 
     * @param message
     *            an error message holding additional information.
     * @param cause
     *            a Throwable that caused this exception.
     */
    public TileSortException(String message, Throwable cause) {
        super(message, cause);
    }

}
