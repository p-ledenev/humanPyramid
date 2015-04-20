package pyramid.solvers;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by ledenev.p on 20.04.2015.
 */

@Component
public class PyramidIndexWeightSolver extends PyramidWeightSolver {

    private int index;

    public PyramidIndexWeightSolver(int level, int index) {
        super(level);
        this.index = index;
    }

    public PyramidIndexWeightSolver() {
        this(0, 0);
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Double computeWeight() {

        validateParams();

        BigInteger combinationsSum = BigInteger.ZERO;
        for (int i = 0; i <= index; i++) {

            System.out.println("Computing combinations (" + (level + 2) + "," + i + ")");
            BigInteger combinationNumber = combinations(level + 2, i);
            //System.out.println("combinations: " + combinationNumber);

            combinationsSum = combinationsSum.add(combinationNumber.multiply(BigInteger.valueOf(1 + index - i)));
        }

        BigDecimal decimalCombinationsSum = new BigDecimal(combinationsSum);
        BigDecimal exponent = BigDecimal.valueOf(2).pow(level);

        return (1 + 2 * index - decimalCombinationsSum.divide(exponent).doubleValue()) * weight;
    }

    private BigInteger combinations(int n, int k) {
        BigInteger nF = factorial(BigInteger.valueOf(n));
        BigInteger kF = factorial(BigInteger.valueOf(k));
        BigInteger nkF = factorial(BigInteger.valueOf(n - k));

        return nF.divide(kF.multiply(nkF));
    }

    private BigInteger factorial(BigInteger n) {

        if (BigInteger.ZERO.equals(n))
            return BigInteger.ONE;

        BigInteger factorial = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {

            if (solverInterrupter.shouldInterrupt())
                solverInterrupter.interrupt();

            factorial = factorial.multiply(i);
        }

        return factorial;
    }

    private void validateParams() {
        if (index > level)
            throw new IncorrectParameterFailure("index greater than level");
    }
}
