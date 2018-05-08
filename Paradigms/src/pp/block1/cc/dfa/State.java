package pp.block1.cc.dfa;

import java.util.Map;
import java.util.TreeMap;

/**
 * State of a DFA.
 */
public class State {
	/** State number */
	private final int nr;

	/** Flag indicating if this state is accepting. */
	private final boolean accepting;

	/** Mapping to next states. */
	private final Map<Character, State> next;

	/**
	 * Constructs a new, possibly accepting state with a given number. The number is
	 * meant to identify the state, but there is no check for uniqueness.
	 */
	public State(int nr, boolean accepting) {
		this.next = new TreeMap<>();
		this.nr = nr;
		this.accepting = accepting;
	}

	/** Returns the state number. */
	public int getNumber() {
		return this.nr;
	}

	/** Indicates if the state is accepting. */
	public boolean isAccepting() {
		return this.accepting;
	}

	/**
	 * Adds an outgoing transition to a next state. This overrides any previous
	 * transition for that character.
	 */
	public void addNext(Character c, State next) {
		this.next.put(c, next);
	}

	/** Indicates if there is a next state for a given character. */
	public boolean hasNext(Character c) {
		return getNext(c) != null;
	}

	/**
	 * Returns the (possibly <code>null</code>) next state for a given character.
	 */
	public State getNext(Character c) {
		return this.next.get(c);
	}

	@Override
	public String toString() {
		String trans = "";
		for (Map.Entry<Character, State> out : this.next.entrySet()) {
			if (!trans.isEmpty()) {
				trans += ", ";
			}
			trans += "--" + out.getKey() + "-> " + out.getValue().getNumber();
		}
		return String.format("State %d (%s) with outgoing transitions %s", this.nr,
				this.accepting ? "accepting" : "not accepting", trans);
	}

	static final public State ID6_DFA;
	static {
		ID6_DFA = new State(0, false);
		State id61 = new State(1, false);
		State id62 = new State(2, false);
		State id63 = new State(3, false);
		State id64 = new State(4, false);
		State id65 = new State(5, false);
		State id66 = new State(6, true);
		State[] states = { ID6_DFA, id61, id62, id63, id64, id65, id66 };
		for (char c = 'a'; c < 'z'; c++) {
			for (int s = 0; s < states.length - 1; s++) {
				states[s].addNext(c, states[s + 1]);
			}
		}
		for (char c = 'A'; c < 'Z'; c++) {
			for (int s = 0; s < states.length - 1; s++) {
				states[s].addNext(c, states[s + 1]);
			}
		}
		for (char c = '0'; c < '9'; c++) {
			for (int s = 1; s < states.length - 1; s++) {
				states[s].addNext(c, states[s + 1]);
			}
		}
	}

	static final public State DFA_LALA;
	static {
		DFA_LALA = new State(0, false);
		State s1 = new State(1, false);
		State s2 = new State(2, true);
		State s3 = new State(3, true);
		State s4 = new State(4, false);
		State s5 = new State(5, true);
		State s6 = new State(6, true);
		State s7 = new State(7, false);
		State s8 = new State(8, false);
		State s9 = new State(9, false);
		State s10 = new State(10, false);
		State s11 = new State(11, true);
		State[] states = { DFA_LALA, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11};
		DFA_LALA.addNext('L', s1);
		s1.addNext('a', s2);
		s2.addNext('a', s2);
		s2.addNext('L', s4);
		s2.addNext(' ', s3);
		s3.addNext(' ', s3);
		s3.addNext('L', s4);
		s4.addNext('a', s5);
		s5.addNext('a', s5);
		s5.addNext('L', s7);
		s5.addNext(' ', s6);
		s6.addNext(' ', s6);
		s6.addNext('L', s7);
		s7.addNext('a', s8);
		s8.addNext('L', s10);
		s8.addNext('a', s8);
		s8.addNext(' ', s9);
		s9.addNext(' ', s9);
		s9.addNext('L', s10);
		s10.addNext('i', s11);
		s10.addNext('a', s8);
		s11.addNext(' ', s11);
	}
}
