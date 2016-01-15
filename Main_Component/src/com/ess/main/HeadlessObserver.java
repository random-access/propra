package com.ess.main;

import java.util.Observable;
import java.util.logging.Logger;

import ess.algorithm.AbstractOutputObservable;
import ess.strings.CustomErrorMessages;
import ess.strings.CustomInfoMessages;

/**
 * An implementation of CompositeObserver that shows info about 
 * the composite in a terminal. It gets notified by 
 * an AbstractOutputObservable if there is a valid composite
 * to be displayed.
 */
public class HeadlessObserver implements ICompositeObserver {

    private Logger log = Logger.getGlobal();
    private ExecMode mode;

    /* (non-Javadoc)
     * @see com.ess.main.CompositeObserver#observe(ess.algorithm.AbstractOutputObservable, com.ess.main.ExecMode)
     */
    @Override
    public void observe(AbstractOutputObservable obs, ExecMode execMode) {
        this.mode = execMode;
        obs.addObserver(this);
        log.info("Added headless observer ...");
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        log.info("Got headless request...");
        AbstractOutputObservable obs = (AbstractOutputObservable) o;
        switch (mode) {
            case SOLVE:
                if (obs.hasValidComposite()) {
                    System.out.println(CustomInfoMessages.INFO_SOLVE_SUCCESS);
                    break;
                } else {
                    System.out.println(CustomInfoMessages.INFO_SOLVE_FAILURE);
                }
                for (String s : obs.getErrors()) {
                    System.out.println(s);
                }
                break;
            case VALIDATE:
                if (obs.hasValidComposite()) {
                    System.out.println(CustomInfoMessages.INFO_VALIDATION_SUCCESS);
                } else {
                    System.out.println(CustomInfoMessages.INFO_VALIDATION_FAILURE);
                }
                break;
           default:
                throw new UnsupportedOperationException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, mode));
        }
        
    }
}
