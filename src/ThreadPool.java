
public class ThreadPool {
	private Integer nroLatinWorkers;
	private Buffer buffer;
	
	public ThreadPool(Integer nroLatinWorkers) {
		this.nroLatinWorkers = nroLatinWorkers;
		this.buffer = new Buffer(100);
		
		for (int i = 0; i < this.nroLatinWorkers; i++) { 
			(new LatinWorker(this.buffer)).start();
		}
	}
	
	public void descartarThreads() throws InterruptedException {
		for (int i = 0; i < this.nroLatinWorkers; i++) { 
			this.buffer.write(new PoisonPill());
		}
	}
	
	public void agregarParaValidar(Validacion validacion) throws InterruptedException {
		this.buffer.write(validacion);
	}
}
