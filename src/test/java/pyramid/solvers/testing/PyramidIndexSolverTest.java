package pyramid.solvers.testing;

import org.junit.Before;
import org.junit.Test;
import pyramid.solvers.PyramidIndexWeightSolver;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by DiKey on 20.04.2015.
 */
public class PyramidIndexSolverTest {

    private PyramidIndexWeightSolver solver;

    @Before
    public void setUp() {
        solver = new PyramidIndexWeightSolver(0, 0, 1);
    }

    @Test
    public void computeIndexWeight1() {

        solver.setLevel(2);
        solver.setIndex(1);

        Double weight = solver.computeWeight();

        assertThat(weight, is(equalTo(3 / 2.)));
    }

    @Test
    public void computeIndexWeight2() {

        solver.setLevel(3);
        solver.setIndex(2);

        Double weight = solver.computeWeight();

        assertThat(weight, is(equalTo(17 / 8.)));
    }

    @Test
    public void computeIndexWeight3() {

        solver.setLevel(4);
        solver.setIndex(1);

        Double weight = solver.computeWeight();

        assertThat(weight, is(equalTo(5 / 2.)));
    }

    @Test
    public void computeIndexWeight4() {

        solver.setLevel(4);
        solver.setIndex(2);

        Double weight = solver.computeWeight();

        assertThat(weight, is(equalTo(25 / 8.)));
    }

    @Test
    public void computeIndexWeight5() {

        solver.setLevel(4);
        solver.setIndex(3);

        Double weight = solver.computeWeight();

        assertThat(weight, is(equalTo(5 / 2.)));
    }

    @Test
    public void computeIndexWeight6() {

        solver.setLevel(5);
        solver.setIndex(2);

        Double weight = solver.computeWeight();

        assertThat(weight, is(equalTo(61 / 16.)));
    }

}
