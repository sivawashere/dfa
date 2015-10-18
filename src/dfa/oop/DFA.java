package dfa.oop;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a deterministic finite automaton (DFA) over a finite set of
 * states Q and an alphabet Σ.
 *
 * @author Siva Somayyajula (sks266@cornell.edu)
 */
public abstract class DFA<Q, Σ> {
    private final Q q0;
    private final Set<Q> F;
    /**
     * Constructs a DFA given the following
     * @param q0 a start state
     * @param F a set of final/accept states
     * */
    @SafeVarargs protected DFA(Q q0, Q... F) {
        this.q0 = q0;
        this.F = new HashSet<>(Arrays.asList(F));
    }
    /**
     * A transition function Q × Σ → Q
     * @param q a state
     * @param σ a symbol from an input string
     * */
    protected abstract Q δ(Q q, Σ σ);
    /**
     * Determines whether or not the automaton accepts:
     * @param input the given input string
     * */
    public final boolean accepts(Iterable<Σ> input) {
        Q q = q0;
        for (Σ σ : input)
            q = δ(q, σ);
        return F.contains(q);
    }
}
