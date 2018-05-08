package pp.block1.cc.test;

import org.junit.Test;

import pp.block1.cc.antlr.PL1;

@SuppressWarnings("javadoc")
public class PL1Test {
	private static LexerTester tester = new LexerTester(PL1.class);
	
	@Test
	public void emptyWords() {
		tester.correct("\"\"");
		tester.correct("");
	}
	
	@Test	
	public void simpleWords() {
		tester.correct("\"abctest\"");
		tester.correct("\"a\"");
		tester.correct("\"1xy,b!\"");
		tester.wrong("\"this\"is wrong\"");
	}
	
	@Test
	public void insertions() {
		tester.correct("\"start\"\"end\"");
		tester.wrong("\"start\"e\"end\"");
		tester.correct("\"\"\"\"");
		tester.correct("\"\"\"start\"\"end\"");
		tester.wrong("\"\"\"");
	}
}
