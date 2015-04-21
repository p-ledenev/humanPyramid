package pyramid.solvers;

import org.springframework.stereotype.Component;

/**
 * Created by ledenev.p on 21.04.2015.
 */

@Component
public class SolversFactory implements ISolversFactory {

    @Override
    public IPyramidWeightSolver createExecutor(int level, int index) {

        double weight = 50;

        if (shouldComputeOnEdge(level, index))
            return new PyramidEdgeWeightSolver(level, weight);

        return new PyramidIndexWeightSolver(level, index, weight);
    }

    private boolean shouldComputeOnEdge(Integer level, Integer index) {
        return index == null || index == 0 || index == level;
    }
}

