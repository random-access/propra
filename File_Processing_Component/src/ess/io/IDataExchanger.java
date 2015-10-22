package ess.io;

import ess.data.Composite;
import ess.io.exc.DataExchangeException;

// TODO: Auto-generated Javadoc
/**
 * The Interface IDataExchanger.
 */
public interface IDataExchanger {
	
	/**
	 * Read from source.
	 *
	 * @param pathToSource the path to source
	 * @return the composite
	 * @throws DataExchangeException the data exchange exception
	 */
	public Composite readFromSource(String pathToSource) throws DataExchangeException; 
	
	/**
	 * Write to target.
	 *
	 * @param composite the composite
	 * @param pathToTarget the path to target
	 * @throws DataExchangeException the data exchange exception
	 */
	public void writeToTarget(Composite composite, String pathToTarget) throws DataExchangeException;
	
}
