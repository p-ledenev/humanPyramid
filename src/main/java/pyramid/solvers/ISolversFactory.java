package pyramid.solvers;

/**
 * Created by ledenev.p on 21.04.2015.
 */
public interface ISolversFactory {

    IPyramidWeightSolver createExecutor(Integer level, Integer index);
}
