package com.ess.main;

import java.util.Observable;
import java.util.logging.Logger;

import ess.algorithm.AbstractOutputObservable;
import ess.strings.CustomErrorMessages;
import ess.strings.CustomInfoMessages;
import ess.utils.CustomLogger;

/**
 * An implementation of <code>CompositeObserver</code> that shows info about 
 * the <code>Composite</code> in a terminal. It gets notified by 
 * an <code>AbstractOutputObservable</code> if there is a valid 
 * Composite to be displayed.
 * 
 * @author Monika Schrenk
 * 
 */
public class HeadlessObserver implements ICompositeObserver {

    private final Logger logger = CustomLogger.getLogger();
    private ExecMode mode;

    /* (non-Javadoc)
     * @see com.ess.main.CompositeObserver#observe(ess.algorithm.AbstractOutputObservable, com.ess.main.ExecMode)
     */
    @Override
    public void observe(AbstractOutputObservable obs, ExecMode execMode) {
        this.mode = execMode;
        obs.addObserver(this);
        logger.info("Added headless observer ...");
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        logger.info("Got headless request...");
        AbstractOutputObservable obs = (AbstractOutputObservable) o;
        if (obs.getErrorMessage() != null) {
            System.out.println(obs.getErrorMessage());
            return;
        }
        System.out.println(constructInfoMessage(obs));
        for (String s : obs.getErrorList()) {
            System.out.println(s);
        }
    }
    
    // puts together a success / error message depending on the mode 
    private String constructInfoMessage(AbstractOutputObservable obs) {
        switch (mode) {
            case SOLVE:
                return obs.hasValidComposite() 
                    ? CustomInfoMessages.INFO_SOLVE_SUCCESS : CustomInfoMessages.INFO_SOLVE_FAILURE;
            case VALIDATE:
                return obs.hasValidComposite() 
                    ? CustomInfoMessages.INFO_VALIDATION_SUCCESS : CustomInfoMessages.INFO_VALIDATION_FAILURE;  
            default:
                throw new UnsupportedOperationException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, mode));  
        }
    }
}
