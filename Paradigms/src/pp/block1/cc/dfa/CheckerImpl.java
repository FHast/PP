package pp.block1.cc.dfa;

public class CheckerImpl implements Checker {

	@Override
	public boolean accepts(State start, String word) {
		if (word.length() == 0) {
			return start.isAccepting();
		}
		char current = word.charAt(0);
		if (start.hasNext(current)) {
			return accepts(start.getNext(current), word.substring(1, word.length()));
		} else {
			return false;
		}
	}
}
