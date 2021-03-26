package edu.unq.tpi.pconc.latinsquarevalidator;

public class LatinWorker extends Thread {
	private Buffer buffer;
	
	public LatinWorker(Buffer buffer) {
		this.buffer = buffer;
	}
	
	public void run() {
		try {
			while (true) {
				Runnable validation = (Runnable) this.buffer.read();
				validation.run();
			}
		} catch (PoisonPillException | InterruptedException exception) {}
	}
}
