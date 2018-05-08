package pp.block2.cp.practical;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import nl.utwente.pp.cp.junit.ConcurrentRunner;
import nl.utwente.pp.cp.junit.Threaded;

@RunWith(ConcurrentRunner.class)
public class UnsafeSequenceTest {
	private static final int AMOUNT_OF_THREADS = 100;

	private final Random random = new Random();

	private UnsafeSequence sequence;
	private int reference;

	private List<Integer> list = new ArrayList<>();

	@Before
	public void before() {
		sequence = new UnsafeSequence();
		reference = -1;
	}

	private synchronized void incrReference() {
		reference++;
	}

	@Test
	@Threaded(count = AMOUNT_OF_THREADS)
	public void concurrencyTest() {
		for (int i = 0; i < 100; i++) {
			int x = sequence.getNext();
			incrReference();
		}
	}

	@After
	public void after() {
		int x = sequence.getNext();
		int y = reference +1;
		System.out.println("expected: " + y);
		System.out.println("actual  : " + x);
		assertTrue(sequence.getNext() == reference + 1);
	}
}
