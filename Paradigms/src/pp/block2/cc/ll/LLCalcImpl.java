package pp.block2.cc.ll;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pp.block2.cc.NonTerm;
import pp.block2.cc.Symbol;
import pp.block2.cc.Term;

public class LLCalcImpl implements LLCalc {
	public Map<Symbol, Set<Term>> first;
	public Map<NonTerm, Set<Term>> follow;
	public Map<Rule, Set<Term>> firstp;

	private Grammar grammar;

	public LLCalcImpl(Grammar g) {
		this.grammar = g;
		this.first = getFirst();
		this.follow = getFollow();
		this.firstp = getFirstp();
	}

	@Override
	public Map<Symbol, Set<Term>> getFirst() {
		// FIRST(T) = T for all terminals
		Map<Symbol, Set<Term>> result = new HashMap<>();
		for (Term t : grammar.getTerminals()) {
			Set<Term> set = new HashSet<>();
			set.add(t);
			result.put(t, set);
		}
		// FIRST(NT) = {} for all nonterminals
		for (NonTerm t : grammar.getNonterminals()) {
			result.put(t, new HashSet<>());
		}
		// add empty
		Set<Term> set = new HashSet<>();
		set.add(Symbol.EMPTY);
		result.put(Symbol.EMPTY, set);
		// add eof
		set = new HashSet<>();
		set.add(Symbol.EOF);
		result.put(Symbol.EOF, set);
		// map of all FIRST lists
		String temp = result.toString();
		do {
			temp = result.toString();
			for (Rule rule : grammar.getRules()) {
				if (!rule.getRHS().isEmpty()) {
					Set<Term> rhs = result.get(rule.getRHS().get(0));
					rhs.remove(Symbol.EMPTY);
					int index = 0;
					while (index <= rule.getRHS().size() - 1
							&& result.get(rule.getRHS().get(index)).contains(Symbol.EMPTY)) {
						for (Term t : result.get(rule.getRHS().get(index + 1))) {
							if (!t.equals(Symbol.EMPTY))
								rhs.add(t);
							index++;
						}
					}
					if (index == rule.getRHS().size() - 1
							&& result.get(rule.getRHS().get(rule.getRHS().size() - 1)).contains(Symbol.EMPTY)) {
						rhs.add(Symbol.EMPTY);
					}
					result.get(rule.getLHS()).addAll(rhs);
				}
			}
		} while (!result.toString().equals(temp));
		return result;
	}

	@Override
	public Map<NonTerm, Set<Term>> getFollow() {
		// init
		Map<NonTerm, Set<Term>> result = new HashMap<>();
		for (NonTerm t : grammar.getNonterminals()) {
			result.put(t, new HashSet<>());
		}
		result.get(grammar.getStart()).add(Symbol.EOF);
		// main
		String temp = result.toString();
		do {
			temp = result.toString();
			for (Rule rule : grammar.getRules()) {
				NonTerm lhs = rule.getLHS();
				List<Symbol> rhs = rule.getRHS();
				Set<Term> trailer = new HashSet<>(result.get(lhs));
				for (int i = rhs.size() - 1; i >= 0; i--) {
					if (rhs.get(i) instanceof NonTerm) {
						result.get(rhs.get(i)).addAll(trailer);
						if (first.get(rhs.get(i)).contains(Symbol.EMPTY)) {
							trailer.addAll(first.get(rhs.get(i)));
							trailer.remove(Symbol.EMPTY);
						} else {
							trailer = new HashSet<>(first.get(rhs.get(i)));
						}
					} else {
						trailer = new HashSet<>(first.get(rhs.get(i)));
					}
				}
			}
		} while (!result.toString().equals(temp));
		return result;
	}

	@Override
	public Map<Rule, Set<Term>> getFirstp() {
		Map<Rule, Set<Term>> result = new HashMap<>();
		for (Rule rule : grammar.getRules()) {
			List<Symbol> rhs = rule.getRHS();
			Set<Term> first = this.first.get(rhs.get(0));
			Set<Term> follow = this.follow.get(rule.getLHS());
			if (first.contains(Symbol.EMPTY)) {
				Set<Term> set = new HashSet<Term>();
				set.addAll(first);
				set.addAll(follow);
				result.put(rule, set);
			} else {
				result.put(rule, new HashSet<>(first));
			}
		}
		return result;
	}

	@Override
	public boolean isLL1() {
		for (NonTerm n : grammar.getNonterminals()) {
			List<Rule> rules = grammar.getRules(n);
			Set<Term> temp = new HashSet<>();
			for (Term t : grammar.getTerminals()) {
				for (Rule rule : rules) {
					if (firstp.get(rule).contains(t)) {
						if (temp.contains(t)) {
							return false;
						} else {
							temp.add(t);
						}
					}
				}
			}
		}
		return true;
	}

}
