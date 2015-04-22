package pyramid.tools.testing;

import org.junit.Test;
import pyramid.tools.CombinatoricsMath;

import java.math.BigInteger;

import static org.junit.Assert.assertTrue;

/**
 * Created by ledenev.p on 21.04.2015.
 */
public class CombinatoricsMathTest {

    @Test
    public void computeCombination11() throws Throwable {

        BigInteger combination = CombinatoricsMath.combinations(4, 0);

        assertTrue(combination.equals(BigInteger.valueOf(1)));
    }

    @Test
    public void computeCombination21() throws Throwable {

        BigInteger combination = CombinatoricsMath.combinations(4, 1);

        assertTrue(combination.equals(BigInteger.valueOf(4)));
    }

    @Test
    public void computeCombination31() throws Throwable {

        BigInteger combination = CombinatoricsMath.combinations(6, 2);

        assertTrue(combination.equals(BigInteger.valueOf(15)));
    }
}
