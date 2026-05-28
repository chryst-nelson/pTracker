package com.BobScript_ng.pTracker.common.exceptionHandler;

public class BadRequestHandler extends AppExceptionHandler {

    public BadRequestHandler(String message) {
        super(message, "BAD_REQUEST");
    }
}
