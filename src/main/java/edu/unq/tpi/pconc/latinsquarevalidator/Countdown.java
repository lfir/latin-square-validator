package edu.unq.tpi.pconc.latinsquarevalidator;

public class Countdown {
	private int ct;
	
	public Countdown(int ct) {
		this.ct = ct;
	}
	
	public synchronized void dec() {
		this.ct--;
		if (this.ct <= 0) {
			notifyAll();
		}
	}
	
	public synchronized void zero() throws InterruptedException {
		while (this.ct > 0) wait();
	}
}
