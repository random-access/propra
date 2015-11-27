package com.ess.main;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import ess.algorithm.AbstractOutputObservable;
import ess.data.Composite;
import ess.ui.ICompositeView;
import ess.ui.MainWindow;

public class DisplayObserver implements Observer {
	
	private Logger log = Logger.getGlobal();
	
	public void observe(AbstractOutputObservable obs) {
		obs.addObserver(this);
		log.info("Added display observer ...");
	}

	@Override
	public void update(Observable o, Object arg) {
		log.info("Got display request...");
		if (o instanceof AbstractOutputObservable) {
			AbstractOutputObservable obs = (AbstractOutputObservable) o;
			Composite c = obs.getComposite();
			ICompositeView view = new MainWindow(c);
			view.display(obs.getErrors());
		}
	}

}
