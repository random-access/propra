package com.ess.main;

import java.util.Observable;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import ess.algorithm.AbstractOutputObservable;
import ess.data.Composite;
import ess.strings.CustomErrorMessages;
import ess.ui.ICompositeView;
import ess.ui.MainWindow;

/**
 * An implementation of CompositeObserver that shows a graphical
 * representation of a composite. It gets notified by 
 * an AbstractOutputObservable if there is a valid composite
 * to be displayed.
 */
public class DisplayObserver implements ICompositeObserver {

    private Logger log = Logger.getGlobal();
    private ExecMode mode;

    /* (non-Javadoc)
     * @see com.ess.main.CompositeObserver#observe(ess.algorithm.AbstractOutputObservable, com.ess.main.ExecMode)
     */
    @Override
    public void observe(AbstractOutputObservable obs, ExecMode execMode) {
        obs.addObserver(this);
        this.mode = execMode;
        log.info("Added display observer ...");
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        log.info("Got display request...");
        final AbstractOutputObservable obs = (AbstractOutputObservable) o;
        Composite c = obs.getComposite();
        final ICompositeView view = new MainWindow(c);
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                switch(mode) {
                    case SOLVE_DISPLAY:
                        view.display(obs.hasValidComposite(), null, obs.getPathToSource(), 's');
                        break;
                    case VALIDATE_DISPLAY:
                        view.display(obs.hasValidComposite(), obs.getErrors(), obs.getPathToSource(), 'v');
                        break;
                    case DISPLAY:
                        view.display(obs.hasValidComposite(), null, obs.getPathToSource(), 'd');
                        break;
                    default:
                        throw new UnsupportedOperationException(String.format(CustomErrorMessages.ERROR_INVALID_ENUM, mode));
                }
            }
        });
    }

}
