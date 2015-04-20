package pyramid.solvers;

/**
 * Created by DiKey on 20.04.2015.
 */
public interface ISolverInterrupter {

    boolean shouldInterrupt();

    void interrupt();
}
