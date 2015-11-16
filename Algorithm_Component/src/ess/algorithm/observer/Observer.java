package ess.algorithm.observer;


public interface Observer<T> {

	public void update( Observable<T> o, T arg );
	
}
