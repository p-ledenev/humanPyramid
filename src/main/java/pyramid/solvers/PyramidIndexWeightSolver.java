package pyramid.solvers;

import pyramid.tools.InterruptedMath;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by ledenev.p on 20.04.2015.
 */

public class PyramidIndexWeightSolver implements IPyramidWeightSolver {

    protected double weight;
    protected int level;
    private int index;

    public PyramidIndexWeightSolver(int level, int index, double weight)
            throws IncorrectParameterFailure {

        if (index > level)
            throw new IncorrectParameterFailure("index greater than level");

        this.level = level;
        this.index = index;
        this.weight = weight;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Double computeWeight() {

        BigInteger combinationsSum = BigInteger.ZERO;
        for (int i = 0; i <= index; i++) {

            System.out.println("Computing combinations (" + (level + 2) + "," + i + ")");
            BigInteger combinationNumber = InterruptedMath.combinations(level + 2, i);
            //System.out.println("combinations: " + combinationNumber);

            combinationsSum = combinationsSum.add(combinationNumber.multiply(BigInteger.valueOf(1 + index - i)));
        }

        BigDecimal decimalCombinationsSum = new BigDecimal(combinationsSum);
        BigDecimal exponent = BigDecimal.valueOf(2).pow(level);

        return (1 + 2 * index - decimalCombinationsSum.divide(exponent).doubleValue()) * weight;
    }
}
