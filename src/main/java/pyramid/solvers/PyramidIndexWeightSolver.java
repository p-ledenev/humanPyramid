package pyramid.solvers;

import pyramid.tools.CombinatoricsMath;

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
            throws IncorrectParameter {

        if (index > level)
            throw new IncorrectParameter("index greater than level");

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
            BigInteger combinationNumber = CombinatoricsMath.combinations(level + 2, i);
            combinationsSum = combinationsSum.add(combinationNumber.multiply(BigInteger.valueOf(1 + index - i)));
        }

        BigDecimal decimalCombinationsSum = new BigDecimal(combinationsSum);
        BigDecimal exponent = BigDecimal.valueOf(2).pow(level);

        Double result = (1 + 2 * index - decimalCombinationsSum.divide(exponent).doubleValue()) * weight;
        System.out.println("Computed value: " + result);

        return result;
    }
}
