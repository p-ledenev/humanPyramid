package pyramid.tools;

import java.math.BigInteger;

/**
 * Created by ledenev.p on 21.04.2015.
 */
public class CombinatoricsMath {

    public static BigInteger combinations(int n, int k) {

        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigK = BigInteger.valueOf(k);
        BigInteger two = BigInteger.valueOf(2);

        BigInteger bigI = (bigK.multiply(two).compareTo(bigN) > 0) ? bigK : bigN.subtract(bigK);

        BigInteger nF = factorialBetween(bigI.add(BigInteger.ONE), bigN);
        BigInteger kF = factorial(bigN.subtract(bigI));


        return nF.divide(kF);
    }

    public static BigInteger factorialBetween(BigInteger k, BigInteger n) {

        BigInteger factorial = BigInteger.ONE;
        for (BigInteger i = k.add(BigInteger.ZERO); i.compareTo(n) <= 0; i = i.add(BigInteger.ONE))
            factorial = factorial.multiply(i);

        return factorial;
    }

    public static BigInteger factorial(BigInteger n) {
        return factorialBetween(BigInteger.ONE, n);
    }
}
