package pyramid.controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import pyramid.solvers.IPyramidWeightSolver;

import java.util.concurrent.*;

/**
 * Created by DiKey on 20.04.2015.
 */

@Component
public class SolverRunner implements ISolverRunner {

    public static final int SOLVING_TIMEOUT = 60;

    private IPyramidWeightSolver solver;

    public void setSolver(IPyramidWeightSolver solver) {
        this.solver = solver;
    }

    public Double run() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Double> future = executor.submit(new Callable<Double>() {

            public Double call() throws Exception {
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
}
