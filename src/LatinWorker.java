
public class LatinWorker extends Thread {
	private Buffer buffer;
	
	public LatinWorker(Buffer buffer) {
		this.buffer = buffer;
	}
	
	public void run() {
		try {
			while (true) {
				Runnable validacion = (Runnable) this.buffer.read();
				validacion.run();
			}
		} catch (PoisonPillException | InterruptedException exception) {}
	}
}
