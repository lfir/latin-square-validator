
public class Buffer {
	private Object[] data;
	private int begin = 0;
	private int end = 0;
	
	public Buffer(int size) {
		this.data = new Object[size + 1];
	}

	public synchronized void write(Object o) throws InterruptedException {
		while (isFull()) wait();
		data[begin] = o;
		begin = next(begin);
		notifyAll();
	}

	public synchronized Object read() throws InterruptedException {
		while (isEmpty()) wait();
		Object result = data[end];
		end = next(end);
		notifyAll();
		return result;
	}

	private boolean isEmpty() { return begin == end; }
	private boolean isFull() { return next(begin) == end; }
	private int next(int i) { return (i + 1) % this.data.length; }
}
