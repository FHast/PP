/**
 * 
 */
package pp.block2.cc.ll;

import pp.block2.cc.NonTerm;
import pp.block2.cc.SymbolFactory;
import pp.block2.cc.Term;

/**
 * Class containing some example grammars.
 * @author Arend Rensink
 *
 */
public class Grammars {
	/** Returns a grammar for simple English sentences. */
	public static Grammar makeSentence() {
		// Define the non-terminals
		NonTerm sent = new NonTerm("Sentence");
		NonTerm subj = new NonTerm("Subject");
		NonTerm obj = new NonTerm("Object");
		NonTerm mod = new NonTerm("Modifier");
		// Define the terminals, using the Sentence.g4 lexer grammar
		// Make sure you take the token constantss from the right class!
		SymbolFactory fact = new SymbolFactory(Sentence.class);
		Term noun = fact.getTerminal(Sentence.NOUN);
		Term verb = fact.getTerminal(Sentence.VERB);
		Term adj = fact.getTerminal(Sentence.ADJECTIVE);
		Term end = fact.getTerminal(Sentence.ENDMARK);
		// Build the context free grammar
		Grammar g = new Grammar(sent);
		g.addRule(sent, subj, verb, obj, end);
		g.addRule(subj, noun);
		g.addRule(subj, mod, subj);
		g.addRule(obj, noun);
		g.addRule(obj, mod, obj);
		g.addRule(mod, adj);
		return g;
	}
	
	public static Grammar lrqGrammar() {
		// Define the non-terminals
		NonTerm l = new NonTerm("L");
		NonTerm r = new NonTerm("R");
		NonTerm rr = new NonTerm("R'");
		NonTerm q = new NonTerm("Q");
		NonTerm qq = new NonTerm("Q'");
		// Define the terminals, using the If.g4 lexer grammar
		// Make sure you take the token constants from the right class!
		SymbolFactory fact = new SymbolFactory(Lrq.class);
		Term a = fact.getTerminal(Lrq.A);
		Term b = fact.getTerminal(Lrq.B);
		Term c = fact.getTerminal(Lrq.C);
		// Build the context free grammar
		Grammar g = new Grammar(l);
		g.addRule(l, r, a);
		g.addRule(l, q, b, a);
		g.addRule(r, a, b, a, rr);
		g.addRule(r, c, a, b, a, rr);
		g.addRule(rr, b, c, rr);
		g.addRule(rr);
		g.addRule(q, b, qq);
		g.addRule(qq, b, c);
		g.addRule(qq, c);
		return g;
	}
}
