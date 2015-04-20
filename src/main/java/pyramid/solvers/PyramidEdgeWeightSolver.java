package pyramid.solvers;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by ledenev.p on 20.04.2015.
 */

@Component
public class PyramidEdgeWeightSolver extends PyramidWeightSolver {

    public PyramidEdgeWeightSolver(int level) {
        super(level);
    }

    public Double computeWeight() {

        BigDecimal exponent = BigDecimal.valueOf(2).pow(level);

        return weight * (BigDecimal.ONE.subtract(BigDecimal.ONE.divide(exponent)).doubleValue());
    }
}
