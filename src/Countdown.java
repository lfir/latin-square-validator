
public class Countdown {
	private int ct;
	
	public Countdown(int ct) {
		this.ct = ct;
	}
	
	public synchronized void dec() {
		this.ct--;
		notifyAll();
	}
	
	public synchronized void zero() throws InterruptedException {
		while (this.ct > 0) wait();
	}
}