package ess.io;

import ess.data.Composite;
import ess.io.exc.DataExchangeException;

/**
 * The Interface IDataExchanger provides methods for reading from an input source and writing to an output target. 
 * Override this interface to implement new input and output methods.
 * {@link #readFromSource(String)} and {@link #writeToTarget(Composite, String)} should also handle conversion between 
 * external data and the corresponding
 * internal representation.
 * Source and target are identified by their paths, which could be paths on the local file system but also network or database
 * paths.
 */
public interface IDataExchanger {
	
	/**
	 * This method reads input from a source, identified by its path, and converts it to the internal data representation.
	 * 
	 * A composite, holding the internal data, is returned.
	 *
	 * @param pathToSource the path to source
	 * @return a composite, holding the internal data
	 * @throws DataExchangeException if an error occurred while reading from the given source
	 */
	Composite readFromSource(String pathToSource) throws DataExchangeException; 
	
	/**
	 * This method converts a composite to its external data representation and appends it to a target identified by its path.
	 * If the target already contains a valid composite, it gets overwritten with the current composite.
	 *
	 * @param composite a composite, holding the internal data
	 * @param pathToTarget the path to target
	 * @throws DataExchangeException if an error occurred while writing to the target
	 */
	void writeToTarget(Composite composite, String pathToTarget) throws DataExchangeException;
	
}
