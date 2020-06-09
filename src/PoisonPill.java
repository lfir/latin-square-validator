
public class PoisonPill implements Runnable {
	@Override
	public void run() {
		throw new PoisonPillException();
	}
}
