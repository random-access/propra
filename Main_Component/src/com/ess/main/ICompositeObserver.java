package com.ess.main;

import java.util.Observer;

import ess.algorithm.AbstractOutputObservable;

/**
 * This interface should be implemented by any class that wants to obtain notifications
 * if solving or validating a composite was successful.
 * 
 * @author Monika Schrenk
 */
public interface ICompositeObserver extends Observer {

    /**
     * Subscribes this instance for obtaining notifications from 
     * AbstractOutputObservable.
     * 
     * @param obs An instance of AbstractOutputObservable that holds information
     * about the composite.
     * @param mode Execution mode entered by the user.
     */
    void observe(AbstractOutputObservable obs, ExecMode mode);

}
