package dfa.oop;

/**
 * A DFA that only accepts strings from the
 * binary alphabet that contain an even number of 0s.
 *
 * @author Siva Somayyajula (sks266@cornell.edu)
 */
public class EvenZeroesDFA extends
        DFA<EvenZeroesDFA.S, BinarySymbol> {
    enum S {
        S1, S2
    }
    public EvenZeroesDFA() {
        super(S.S1, S.S1);
    }
    @Override
    protected S δ(S q, BinarySymbol σ) {
        switch (σ) {
            case Z:
                switch (q) {
                    case S1:
                        return S.S2;
                    default:
                        return S.S1;
                }
            case O:
                switch (q) {
                    case S1:
                        return S.S1;
                    default:
                        return S.S2;
                }
        }
        throw new RuntimeException("This shouldn't happen");
    }
}
