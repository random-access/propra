package com.ess.main;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import ess.algorithm.OutputObservable;

public class HeadlessObserver implements Observer{

	Logger log = Logger.getGlobal();
	
	public void observe(OutputObservable obs) {
		obs.addObserver(this);
		log.info("Added headless observer ...");
	}

	@Override
	public void update(Observable o, Object arg) {
		log.info("Got headless request...");
		if (o instanceof OutputObservable) {
			OutputObservable obs = (OutputObservable) o;
			for (String s : obs.getErrors()) {
				System.out.println(s);
			}
		}
	}
}
