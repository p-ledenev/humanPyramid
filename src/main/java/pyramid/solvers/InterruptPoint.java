package pyramid.solvers;

/**
 * Created by DiKey on 20.04.2015.
 */

public class InterruptPoint {

    public static void pass() throws InterruptedException {

        if (!Thread.interrupted())
            return;

        throw new InterruptedException();
    }
}
