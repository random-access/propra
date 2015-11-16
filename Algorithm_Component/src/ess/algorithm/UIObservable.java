package ess.algorithm;

import java.util.Observable;

import ess.data.Composite;

public abstract class UIObservable extends Observable {
	
	public abstract Composite getComposite();
	
	public abstract String[] getErrors();

}
