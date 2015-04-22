package pyramid.controllers;

import pyramid.solvers.IPyramidWeightSolver;

/**
 * Created by ledenev.p on 21.04.2015.
 */
public interface ISolverRunner {

    Double run();

    void setSolver(IPyramidWeightSolver solver);
}
