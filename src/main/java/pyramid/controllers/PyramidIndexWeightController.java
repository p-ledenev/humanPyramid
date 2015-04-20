package pyramid.controllers;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pyramid.solvers.IHumanPyramidWeightSolver;
import pyramid.solvers.IncorrectParameterFailure;

import java.util.concurrent.*;

/**
 * Created by DiKey on 19.04.2015.
 */

@Controller
public class PyramidIndexWeightController {

    public static final int SOLVING_TIMEOUT = 3;

    @Autowired
    private IHumanPyramidWeightSolver solver;

    @RequestMapping(value = "/humanEdgeWeight", method = RequestMethod.GET)
    @ResponseBody
    public String handleRequest(@RequestParam("level") final Integer level,
                                @RequestParam(value = "index", required = false) final Integer index) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return process(level, index);
            }
        });

        try {
            return future.get(SOLVING_TIMEOUT, TimeUnit.SECONDS);

        } catch (TimeoutException e) {
            future.cancel(true);
            throw new TimeoutFailure();

        } catch (Throwable e) {
            System.out.println("Exception: " + ExceptionUtils.getStackTrace(e));
            throw new InternalServerFailure();
        } finally {
            executor.shutdown();
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
        return "TimeoutFailure: timeout within " + SOLVING_TIMEOUT + "sec.";
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
}
