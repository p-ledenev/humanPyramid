package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by DiKey on 19.04.2015.
 */

@Controller
public class PyramidIndexWeightController {

    @RequestMapping(value = "/humanEdgeWeight", method = RequestMethod.GET)
    @ResponseBody
    public String handleRequest(@RequestParam("level") Long level, @RequestParam(value = "index", required = false) Long index) {
        return "level: " + level + "; index: " + index;
    }
}
