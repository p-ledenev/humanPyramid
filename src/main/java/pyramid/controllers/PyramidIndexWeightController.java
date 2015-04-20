package pyramid.controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pyramid.solvers.IHumanPyramidWeightSolver;

import java.util.concurrent.*;

/**
 * Created by DiKey on 19.04.2015.
 */

@Controller
public class PyramidIndexWeightController {

    public static int SolverTimeout = 40;

    @Autowired
    private IHumanPyramidWeightSolver solver;

    @RequestMapping(value = "/humanEdgeWeight", method = RequestMethod.GET)
    @ResponseBody
    public String handleRequest(@RequestParam("level") final Integer level,
                                @RequestParam(value = "index", required = false) final Integer index) {

        validateParams(level, index);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return process(level, index);
            }
        });

        try {
            return future.get(SolverTimeout, TimeUnit.SECONDS);

        } catch (TimeoutException e) {
            throw new TimeoutFailure();

        } catch (Throwable e) {
            System.out.println("Exception: " + ExceptionUtils.getStackTrace(e));
            throw new InternalServerFailure();
        }
    }

    @ExceptionHandler(IncorrectParameterFailure.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIncorrectParameterFailure(IncorrectParameterFailure e) {
        return "IncorrectParameterFailure: " + e.getMessage();
    }

    @ExceptionHandler(TimeoutFailure.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ResponseBody
    public String handleTimeoutFailure() {
        return "TimeoutFailure: timeout within " + SolverTimeout + "sec.";
    }

    @ExceptionHandler(InternalServerFailure.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleInternalServerFailure() {
        return "InternalServerFailure";
    }

    private String process(Integer level, Integer index) {
        if (shouldComputeOnEdge(level, index))
            return solver.computeHumanEdgeWeightOn(level).toString();

        return solver.computeHumanIndexWeightOn(level, index).toString();
    }

    private boolean shouldComputeOnEdge(Integer level, Integer index) {
        return index == null || index == 0 || index == level;
    }

    private void validateParams(Integer level, Integer index) {

        if (index != null && index > level)
            throw new IncorrectParameterFailure("level greater than index");
    }
}
