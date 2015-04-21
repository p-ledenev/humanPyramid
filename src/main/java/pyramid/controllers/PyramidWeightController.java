package pyramid.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pyramid.solvers.IPyramidWeightSolver;
import pyramid.solvers.ISolversFactory;
import pyramid.solvers.IncorrectParameter;

/**
 * Created by DiKey on 19.04.2015.
 */

@Controller
public class PyramidWeightController {

    @Autowired
    private ISolverRunner solverExecutor;

    @Autowired
    private ISolversFactory solversFactory;

    @RequestMapping(value = "/humanEdgeWeight", method = RequestMethod.GET)
    @ResponseBody
    public String handleRequest(@RequestParam("level") final Integer level,
                                @RequestParam(value = "index", required = false) final Integer index) {

        IPyramidWeightSolver solver = solversFactory.createExecutor(level, index);
        solverExecutor.setSolver(solver);

        return solverExecutor.run().toString();
    }

    @ExceptionHandler(IncorrectParameter.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleIncorrectParameterFailure(IncorrectParameter e) {
        return "IncorrectParameterFailure: " + e.getMessage();
    }

    @ExceptionHandler(TimeoutFailure.class)
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    @ResponseBody
    public String handleTimeoutFailure() {
        return "TimeoutFailure: timeout within " + SolverRunner.SOLVING_TIMEOUT + "sec.";
    }

    @ExceptionHandler(InternalServerFailure.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleInternalServerFailure() {
        return "InternalServerFailure";
    }
}
