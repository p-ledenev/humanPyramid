package pyramid.controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import pyramid.solvers.IPyramidWeightSolver;
import pyramid.solvers.PyramidEdgeWeightSolver;
import pyramid.solvers.PyramidIndexWeightSolver;

import java.util.concurrent.*;

/**
 * Created by DiKey on 20.04.2015.
 */

@Component
public class SolverExecutor {

    public static final int SOLVING_TIMEOUT = 3;

    public Double execute(final Integer level, final Integer index) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> future = executor.submit(new Callable<Double>() {

            public Double call() throws Exception {
                IPyramidWeightSolver solver = createSolver(level, index);
                return solver.computeWeight();
            }
        });

        try {
            return future.get(SOLVING_TIMEOUT, TimeUnit.SECONDS);

        } catch (TimeoutException e) {
            future.cancel(true);
            throw new TimeoutFailure();

        } catch (Throwable e) {
            System.out.println("Exception: " + ExceptionUtils.getStackTrace(e));
            throw new InternalServerFailure();
        } finally {
            executor.shutdown();
        }
    }

    private IPyramidWeightSolver createSolver(Integer level, Integer index) {
        if (shouldComputeOnEdge(level, index))
            return new PyramidEdgeWeightSolver(level);

        return new PyramidIndexWeightSolver(level, index);
    }

    private boolean shouldComputeOnEdge(Integer level, Integer index) {
        return index == null || index == 0 || index == level;
    }
}
