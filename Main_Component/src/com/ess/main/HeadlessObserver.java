package com.ess.main;

import java.util.Observable;
import java.util.logging.Logger;

import ess.algorithm.AbstractOutputObservable;
import ess.strings.CustomErrorMessages;

/**
 * An implementation of CompositeObserver that shows info about 
 * the composite in a terminal. It gets notified by 
 * an AbstractOutputObservable if there is a valid composite
 * to be displayed.
 */
public class HeadlessObserver implements CompositeObserver {

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
            case VALIDATE:
                for (String s : obs.getErrors()) {
                    System.out.println(s);
                }
                break;
            default:
                throw new UnsupportedOperationException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, mode));
        }
        
    }
}
