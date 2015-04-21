package pyramid.solvers;

/**
 * Created by ledenev.p on 21.04.2015.
 */

public class SolversFactory implements ISolversFactory {

    private double weight;

    public SolversFactory(double weight) {
        this.weight = weight;
    }

    @Override
    public IPyramidWeightSolver createExecutor(Integer level, Integer index) {

        if (shouldComputeOnEdge(level, index))
            return new PyramidEdgeWeightSolver(level, weight);

        return new PyramidIndexWeightSolver(level, index, weight);
    }

    private boolean shouldComputeOnEdge(Integer level, Integer index) {
        return index == null || index == 0 || index == level;
    }
}

