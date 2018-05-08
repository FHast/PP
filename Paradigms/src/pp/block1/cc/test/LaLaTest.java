package pp.block1.cc.test;

import org.junit.Test;

import pp.block1.cc.antlr.LaLa;

@SuppressWarnings("javadoc")
public class LaLaTest {
	private static LexerTester tester = new LexerTester(LaLa.class);
	
	@Test
	public void simple() {
		tester.correct("");
		tester.wrong("abc");
	}
	
	@Test
	public void lalas() {
		tester.correct("Laaaa LaLaa");
		tester.correct("Laaa    ");
		tester.wrong("Li");
		tester.wrong("Laaa L a ");
	}
	
	@Test 
	public void complex() {
		tester.correct("LaaaaLaLaa Laaaa    LaLiLaa");
		tester.wrong("Laaa LaLi Laaa");
		tester.yields("Laaaa LaLaa", LaLa.DOUBLE, LaLa.SIMPLE);
		tester.wrong("La La La La Li");
	}

}
