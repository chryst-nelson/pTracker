package com.BobScript_ng.pTracker.common.exceptionHandler;

public class DuplicationException extends AppExceptionHandler {

    public DuplicationException(String message) {

        super(message, "CONFLICT");
    }

}
