package pyramid.solvers;

/**
 * Created by DiKey on 20.04.2015.
 */
public class SolverInterrupter implements ISolverInterrupter {

    public boolean shouldInterrupt() {
        return Thread.interrupted();
    }

    public void interrupt() {
        System.out.println("Computation aborted");
        throw new SolverInterruptedFailure();
    }
}
