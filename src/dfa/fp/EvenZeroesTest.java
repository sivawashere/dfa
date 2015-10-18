package dfa.fp;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Tests a DFA that only accepts strings from the
 * binary alphabet that contain an even number of 0s.
 */
@RunWith(Theories.class)
public class EvenZeroesTest {
    enum S {
        S1, S2
    }
    enum Σ {
        Z, O // (Z)ero or (O)ne
    }
    private final DFA<S, Σ> even0 = new DFA<>(S.S1, (q, σ) -> {
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
        return null;
    }, S.S1);
    @DataPoints
    public static Iterable<Σ>[] strings() {
        Random r = new Random();
        Iterable<Σ>[] strings =
                new Iterable[r.nextInt(1000) + 1];
        for (int i = 0; i < strings.length; i++) {
            List<Σ> string = new LinkedList<>();
            int l = r.nextInt(1000);
            for (int j = 0; j < l; j++)
                string.add(r.nextBoolean() ? Σ.O : Σ.Z);
            strings[i] = string;
        }
        return strings;
    }
    public static boolean isEven0(Iterable<Σ> input) {
        // Independently determines whether
        // input should be accepted by the DFA
        int count = 0;
        for (Σ σ : input) {
            if (σ == Σ.Z)
                count++;
        }
        return count % 2 == 0;
    }
    @Theory
    public void test(Iterable<Σ> input) throws Exception {
        assertTrue(isEven0(input) == even0.accepts(input));
    }
}