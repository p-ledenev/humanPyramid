package pyramid.solvers;

import org.apache.commons.math3.util.FastMath;
import org.springframework.stereotype.Component;
import pyramid.controllers.InternalServerFailure;

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
        return weight0 * (1 - 1 / Math.pow(2, level));
    }

    @Override
    public Double computeHumanIndexWeightOn(int level, int index) {

        long combinationNumberSum = 0;
        for (int i = 0; i < index; i++) {

            if (Thread.interrupted())
                throw new InternalServerFailure();

            System.out.println("Computing combinationNumber(" + (level + 2) + "," + i + ")");
            combinationNumberSum += combinationNumber(level + 2, i) * (1 + index - i);
        }

        return (1 + 2 * index - combinationNumberSum / FastMath.pow(2, level)) * weight0;
    }

    private int combinationNumber(int n, int k) {
        return 0;
    }


}
