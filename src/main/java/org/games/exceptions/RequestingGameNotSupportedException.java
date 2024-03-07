package org.games.exceptions;

public class RequestingGameNotSupportedException extends RuntimeException {

    private static final String MESSAGE = "Requested game not supported!";
    public RequestingGameNotSupportedException() {
        super(MESSAGE);
    }
}
