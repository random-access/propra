package com.ess.main;

import java.util.Observable;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import ess.algorithm.AbstractOutputObservable;
import ess.data.Composite;
import ess.strings.CustomErrorMessages;
import ess.ui.ICompositeView;
import ess.ui.MainWindow;
import ess.utils.CustomLogger;

/**
 * An implementation of <code>CompositeObserver<code> that shows a graphical
 * representation of a <code>Composite</code>. It gets notified by 
 * an <code>AbstractOutputObservable</code> if there is a valid Composite
 * to be displayed.
 */
public class DisplayObserver implements ICompositeObserver {

    private final Logger logger = CustomLogger.getLogger();
    private ExecMode mode;

    /* (non-Javadoc)
     * @see com.ess.main.CompositeObserver#observe(ess.algorithm.AbstractOutputObservable, com.ess.main.ExecMode)
     */
    @Override
    public void observe(AbstractOutputObservable obs, ExecMode execMode) {
        obs.addObserver(this);
        this.mode = execMode;
        logger.info("Added display observer ...");
    }

    /* (non-Javadoc)
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        logger.info("Got display request...");
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
