package com.ess.main;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

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
			final AbstractOutputObservable obs = (AbstractOutputObservable) o;
			Composite c = obs.getComposite();
			final ICompositeView view = new MainWindow(c);
			SwingUtilities.invokeLater(new Runnable() {
                
                @Override
                public void run() {
                    view.display(obs.getErrors());
                }
            });
			
		}
	}

}
