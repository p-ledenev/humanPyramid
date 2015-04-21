package pyramid.tools;

import pyramid.solvers.InterruptPoint;

import java.math.BigInteger;

/**
 * Created by ledenev.p on 21.04.2015.
 */
public class InterruptibleMath {

    public static BigInteger combinations(int n, int k) throws InterruptedException {

        BigInteger bigN = BigInteger.valueOf(n);
        BigInteger bigK = BigInteger.valueOf(k);
        BigInteger two = BigInteger.valueOf(2);

        BigInteger bigI = (bigK.multiply(two).compareTo(bigN) > 0) ? bigK : bigN.subtract(bigK);

        BigInteger nF = factorialBetween(bigI.add(BigInteger.ONE), bigN);
        BigInteger kF = factorial(bigN.subtract(bigI));

        return nF.divide(kF);
    }

    public static BigInteger factorialBetween(BigInteger k, BigInteger n) throws InterruptedException {

        BigInteger factorial = BigInteger.ONE;
        for (BigInteger i = k.add(BigInteger.ZERO); i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {

            InterruptPoint.pass();

            factorial = factorial.multiply(i);
        }

        return factorial;
    }

    public static BigInteger factorial(BigInteger n) throws InterruptedException {
        return factorialBetween(BigInteger.ONE, n);
    }
}
