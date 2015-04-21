package pyramid.solvers;

/**
 * Created by DiKey on 20.04.2015.
 */

public class InterruptPoint {

    public static void pass() throws SolverInterruptedFailure {

        if (!Thread.interrupted())
            return;

        System.out.println("Computation aborted");
        throw new SolverInterruptedFailure();
    }
}
