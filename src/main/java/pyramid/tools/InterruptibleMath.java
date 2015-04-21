package pyramid.tools;

import pyramid.solvers.InterruptPoint;

import java.math.BigInteger;

/**
 * Created by ledenev.p on 21.04.2015.
 */
public class InterruptibleMath {

    public static BigInteger combinations(int n, int k) throws InterruptedException {
        BigInteger nF = factorial(BigInteger.valueOf(n));
        BigInteger kF = factorial(BigInteger.valueOf(k));
        BigInteger nkF = factorial(BigInteger.valueOf(n - k));

        return nF.divide(kF.multiply(nkF));
    }

    public static BigInteger factorial(BigInteger n) throws InterruptedException {

        if (BigInteger.ZERO.equals(n))
            return BigInteger.ONE;

        BigInteger factorial = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {

            InterruptPoint.pass();

            factorial = factorial.multiply(i);
        }

        return factorial;
    }
}
