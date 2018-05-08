package pp.block2.cp.practical;

public class SafeSequence {
	private int value;
	
	public synchronized int getNext() {
		return value++;
	}
}
