package ess.io;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.TransformerException;

/**
 * A customized <code>ErrorListener</code> to be used for avoiding additional prints to 
 * <code>System.err</code> from a <code>Transformer</code> by just re-throwing
 * all exceptions that occur.
 * 
 * @author Monika Schrenk
 *
 */
public class XMLTransformerErrorListener implements ErrorListener {

    /**
     * Re-throws the exception if a notification of a warning is received.
     * 
     * @see javax.xml.transform.ErrorListener#warning(javax.xml.transform.TransformerException)
     */
    @Override
    public void warning(TransformerException exception) throws TransformerException {
        throw exception;
    }

    /**
     * Re-throws the exception if a notification of an error is received.
     * 
     * @see javax.xml.transform.ErrorListener#error(javax.xml.transform.TransformerException)
     */
    @Override
    public void error(TransformerException exception) throws TransformerException {
        throw exception;
    }

    /**
     * Re-throws the exception if a notification of a fatal error is received.
     * 
     * @see javax.xml.transform.ErrorListener#fatalError(javax.xml.transform.TransformerException)
     */
    @Override
    public void fatalError(TransformerException exception) throws TransformerException {
        throw exception;
    }
}
