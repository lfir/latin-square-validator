package edu.unq.tpi.pconc.latinsquarevalidator;

public class ThreadPool {
	private Integer numberOfLatinWorkers;
	private Buffer buffer;
	
	public ThreadPool(Integer numberOfLatinWorkers) {
		this.numberOfLatinWorkers = numberOfLatinWorkers;
		this.buffer = new Buffer(100);
		
		for (int i = 0; i < this.numberOfLatinWorkers; i++) { 
			(new LatinWorker(this.buffer)).start();
		}
	}
	
	public void discardThreads() throws InterruptedException {
		for (int i = 0; i < this.numberOfLatinWorkers; i++) { 
			this.buffer.write(new PoisonPill());
		}
	}
	
	public void addTask(Validation validation) throws InterruptedException {
		this.buffer.write(validation);
	}
}
