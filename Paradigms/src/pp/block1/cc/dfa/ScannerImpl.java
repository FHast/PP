package pp.block1.cc.dfa;

import java.util.ArrayList;
import java.util.List;

public class ScannerImpl implements Scanner {

	@Override
	public List<String> scan(State dfa, String text) {
		List<String> tokens = new ArrayList<String>();
		String token = "";
		State state = dfa;
		String rest = text;
		char c;
		while (!rest.equals("")) {
			c = rest.charAt(0);
			rest = rest.substring(1);
			if (state.hasNext(c)) {
				token += c;
				state = state.getNext(c);
			} else {
				tokens.add(token);
				token = "";	
				token += c;
				state = dfa;
			}
		}
		if (!token.equals("")) {
			tokens.add(token);
		}
		return tokens;
	}

}
