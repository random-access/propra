package ess.io;

import ess.data.Composite;
import ess.io.exc.DataExchangeException;

public interface IDataExchanger {
	
	public Composite readFromSource(String pathToSource) throws DataExchangeException; 
	
	public void writeToTarget(Composite composite, String pathToTarget) throws DataExchangeException;
	
}
