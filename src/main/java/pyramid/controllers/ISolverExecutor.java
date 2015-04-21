package pyramid.controllers;

import pyramid.solvers.IPyramidWeightSolver;

/**
 * Created by ledenev.p on 21.04.2015.
 */
public interface ISolverExecutor {

    Double execute();

    void setSolver(IPyramidWeightSolver solver);
}
