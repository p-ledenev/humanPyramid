package pyramid.controllers;

import pyramid.solvers.IPyramidWeightSolver;

/**
 * Created by ledenev.p on 22.04.2015.
 */

public class UnsafeSolverRunner implements ISolverRunner {

    private IPyramidWeightSolver solver;

    public void setSolver(IPyramidWeightSolver solver) {
        this.solver = solver;
    }

    public Double run() {

        final Double[] result = new Double[1];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                result[0] = solver.computeWeight();
            }
        });

        thread.start();

        try {
            thread.join(1000 * SOLVING_TIMEOUT);
        } catch (InterruptedException e) {
            System.out.println("Solver thread was interrupted");
        }

        if (result[0] == null) {
            thread.stop();
            System.out.println("Solver thread stopped");
            throw new TimeoutFailure();
        }

        return result[0];
    }

}
