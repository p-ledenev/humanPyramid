package pyramid.solvers;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by DiKey on 20.04.2015.
 */
public abstract class PyramidWeightSolver implements IPyramidWeightSolver {

    protected double weight;
    protected int level;

    protected ISolverInterrupter solverInterrupter;

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Autowired
    public void setSolverInterrupter(ISolverInterrupter solverInterrupter) {
        this.solverInterrupter = solverInterrupter;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public PyramidWeightSolver(int level, double weight) {
        this.level = level;
        this.weight = weight;
    }

    public PyramidWeightSolver(int level) {
        this(level, 50);
    }
}
