package ess.utils;

public class PropertyException extends Exception {

	private static final long serialVersionUID = 1L;

	public PropertyException(String message) {
		super(message);
	}

	public PropertyException(Throwable cause) {
		super(cause);
	}

	public PropertyException(String message, Throwable cause) {
		super(message, cause);
	}

	public PropertyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
