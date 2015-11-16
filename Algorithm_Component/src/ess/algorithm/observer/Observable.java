package ess.algorithm.observer;

import java.util.ArrayList;

public class Observable<T> {

	private final ArrayList<Observer<T>> observers = new ArrayList<>();
	private volatile boolean changed;

	public synchronized void addObserver(Observer<T> observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	public synchronized void deleteObserver(Observer<?> observer) {
		observers.remove(observer);
	}

	public void setChanged() {
		changed = true;
	}

	public synchronized void notifyObservers(T arg) {
		if (changed) {
			for (Observer<T> observer : observers) {
				observer.update(this, arg);
			}
			changed = false;
		}
	}
}
