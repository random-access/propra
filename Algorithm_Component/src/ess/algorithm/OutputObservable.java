package ess.algorithm;

import java.util.Observable;

import ess.data.Composite;

public abstract class OutputObservable extends Observable {
	
	public abstract Composite getComposite();
	
	public abstract String[] getErrors();

}
