package pyramid.solvers;

import com.google.common.math.BigIntegerMath;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by ledenev.p on 20.04.2015.
 */

@Component
public class HumanPyramidWeightSolver implements IHumanPyramidWeightSolver {

    private double weight0;

    public HumanPyramidWeightSolver(double weight0) {
        this.weight0 = weight0;
    }

    public HumanPyramidWeightSolver() {
        this(50);
    }

    @Override
    public Double computeHumanEdgeWeightOn(int level) {

        BigDecimal exponent = BigDecimal.valueOf(2).pow(level);

        return weight0 * (BigDecimal.ONE.subtract(BigDecimal.ONE.divide(exponent)).doubleValue());
    }

    @Override
    public Double computeHumanIndexWeightOn(int level, int index) {

        BigInteger combinationsSum = BigInteger.ZERO;
        for (int i = 0; i <= index; i++) {

            if (Thread.interrupted()) {
                System.out.println("Computing combinations aborted");
                throw new SolverInterruptedFailure();
            }

            System.out.println("Computing combinations (" + (level + 2) + "," + i + ")");
            BigInteger combinationNumber = combinations(level + 2, i);
            //System.out.println("combinations: " + combinationNumber);

            combinationsSum = combinationsSum.add(combinationNumber.multiply(BigInteger.valueOf(1 + index - i)));
        }

        BigDecimal decimalCombinationsSum = new BigDecimal(combinationsSum);
        BigDecimal exponent = BigDecimal.valueOf(2).pow(level);

        return (1 + 2 * index - decimalCombinationsSum.divide(exponent).doubleValue()) * weight0;
    }

    private BigInteger combinations(int n, int k) {
        BigInteger nF = BigIntegerMath.factorial(n);
        BigInteger kF = BigIntegerMath.factorial(k);
        BigInteger nkF = BigIntegerMath.factorial(n - k);

        return nF.divide(kF.multiply(nkF));
    }


}
