package pp.block2.cp.practical;

public class UnsafeSequence {
	private int value;

	/** Returns a unique value. */
	public int getNext() {
		return value++;
	}
}
