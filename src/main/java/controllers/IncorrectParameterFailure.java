package controllers;

/**
 * Created by DiKey on 19.04.2015.
 */
public class IncorrectParameterFailure extends RuntimeException {

    public IncorrectParameterFailure(String message) {
        super(message);
    }
}
