package pyramid.solvers;

import pyramid.tools.InterruptibleMath;

import java.math.BigDecimal;
import java.math.BigInteger;

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
        try {
            //BigDecimal exponent = BigDecimal.valueOf(2).pow(level);
            BigDecimal exponent = InterruptibleMath.exponential(BigInteger.valueOf(level));

            return weight * (BigDecimal.ONE.subtract(BigDecimal.ONE.divide(exponent)).doubleValue());

        } catch (InterruptedException e) {
            System.out.println("Solver interrupted");
            throw new SolverInterrupted();
        }
    }
}
