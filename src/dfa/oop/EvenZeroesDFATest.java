package dfa.oop;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Tests the validity of EvenZeroesDFA
 *
 * @author Siva Somayyajula (sks266@cornell.edu)
 */
@RunWith(Theories.class)
public class EvenZeroesDFATest {
    private final EvenZeroesDFA even0 = new EvenZeroesDFA();
    @DataPoints
    public static Iterable<BinarySymbol>[] strings() {
        Random r = new Random();
        Iterable<BinarySymbol>[] strings =
                new Iterable[r.nextInt(1000) + 1];
        for (int i = 0; i < strings.length; i++) {
            List<BinarySymbol> string = new LinkedList<>();
            int l = r.nextInt(1000);
            for (int j = 0; j < l; j++)
                string.add(r.nextBoolean() ?
                        BinarySymbol.O :
                        BinarySymbol.Z);
            strings[i] = string;
        }
        return strings;
    }
    boolean isEven0(Iterable<BinarySymbol> input) {
        // Independently determines whether input should be accepted
        int count = 0;
        for (BinarySymbol σ : input) {
            if (σ == BinarySymbol.Z)
                count++;
        }
        return count % 2 == 0;
    }
    @Theory
    public void test(Iterable<BinarySymbol> input) throws Exception {
        assertTrue(isEven0(input) == even0.accepts(input));
    }
}