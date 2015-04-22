package pyramid.solvers;

import java.math.BigDecimal;

/**
 * Created by ledenev.p on 20.04.2015.
 */

public class PyramidEdgeWeightSolver implements IPyramidWeightSolver {

    protected double weight;
    protected int level;

    public PyramidEdgeWeightSolver(int level, double weight) {
        this.weight = weight;
        this.level = level;
    }

    public Double computeWeight() {

        BigDecimal exponent = BigDecimal.valueOf(2).pow(level);

        return weight * (BigDecimal.ONE.subtract(BigDecimal.ONE.divide(exponent)).doubleValue());
    }
}
