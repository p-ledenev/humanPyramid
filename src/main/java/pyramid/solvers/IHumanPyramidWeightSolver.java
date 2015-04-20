package pyramid.solvers;

/**
 * Created by ledenev.p on 20.04.2015.
 */
public interface IHumanPyramidWeightSolver {

    Double computeHumanEdgeWeightOn(int level);

    Double computeHumanIndexWeightOn(int level, int index);
}
