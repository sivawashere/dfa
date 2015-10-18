package dfa.fp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * Represents a deterministic finite automaton (DFA)
 * over a finite set of states Q and an alphabet Σ.
 *
 * @author Siva Somayyajula (sks266@cornell.edu)
 */
public class DFA<Q, Σ> {
    private final Q q0;
    private final BiFunction<Q, Σ, Q> δ;
    private final Set<Q> F;
    /**
     * Constructs a DFA given the following
     * @param q0 a start state
     * @param δ a transition function Q × Σ → Q
     * @param F a set of accept states
     * */
    @SafeVarargs
    public DFA(Q q0, BiFunction<Q, Σ, Q> δ, Q... F) {
        this.q0 = q0;
        this.δ = δ;
        this.F = new HashSet<>(Arrays.asList(F));
    }
    /**
     * Determines whether or not the automaton accepts:
     * @param input the given input string
     * */
    public boolean accepts(Iterable<Σ> input) {
        Q q = q0;
        for (Σ σ : input)
            q = δ.apply(q, σ);
        return F.contains(q);
    }
}