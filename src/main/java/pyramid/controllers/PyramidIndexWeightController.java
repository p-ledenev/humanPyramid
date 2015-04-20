package pyramid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pyramid.solvers.IPyramidWeightSolver;
import pyramid.solvers.IncorrectParameterFailure;

/**
 * Created by DiKey on 19.04.2015.
 */

@Controller
public class PyramidIndexWeightController {

    @Autowired
    private SolverExecutor solverExecutor;

    @RequestMapping(value = "/humanEdgeWeight", method = RequestMethod.GET)
    @ResponseBody
    public String handleRequest(@RequestParam("level") final Integer level,
                                @RequestParam(value = "index", required = false) final Integer index) {

        return solverExecutor.execute(level, index).toString();
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
        return "TimeoutFailure: timeout within " + SolverExecutor.SOLVING_TIMEOUT + "sec.";
    }

    @ExceptionHandler(InternalServerFailure.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleInternalServerFailure() {
        return "InternalServerFailure";
    }
}
