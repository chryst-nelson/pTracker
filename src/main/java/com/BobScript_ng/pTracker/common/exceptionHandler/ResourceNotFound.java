package com.BobScript_ng.pTracker.common.exceptionHandler;

public class ResourceNotFound extends AppExceptionHandler {

    public ResourceNotFound(String message) {
        super(message, "RESOURCE_NOT_FOUND");
    }
}
