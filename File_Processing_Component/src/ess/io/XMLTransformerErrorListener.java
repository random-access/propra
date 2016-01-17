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

    @Override
    public void warning(TransformerException exception) throws TransformerException {
        throw exception;
    }

    @Override
    public void error(TransformerException exception) throws TransformerException {
        throw exception;
    }

    @Override
    public void fatalError(TransformerException exception) throws TransformerException {
        throw exception;
    }

  
}
