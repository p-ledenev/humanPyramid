package pyramid.controllers;

import pyramid.solvers.IPyramidWeightSolver;

/**
 * Created by ledenev.p on 21.04.2015.
 */
public interface ISolverRunner {

    public static final int SOLVING_TIMEOUT = 10;

    Double run();

    void setSolver(IPyramidWeightSolver solver);
}
