package ess.io;

import ess.data.Composite;
import ess.io.exc.DataExchangeException;

// TODO: Auto-generated Javadoc
/**
 * The Class XMLDataExchanger.
 */
public class XMLDataExchanger implements IDataExchanger {

	/*
	 * (non-Javadoc)
	 * 
	 * @see ess.io.IDataExchanger#readFromSource(java.lang.String)
	 */
	@Override
	public Composite readFromSource(String pathToSource) throws DataExchangeException {
		return new XMLDataImporter().importComposite(pathToSource);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ess.io.IDataExchanger#writeToTarget(ess.data.Composite,
	 * java.lang.String)
	 */
	@Override
	public void writeToTarget(Composite composite, String pathToTarget) throws DataExchangeException {
		// TODO Auto-generated method stub

	}

}
