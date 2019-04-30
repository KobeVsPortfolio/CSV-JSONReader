package com.realdolmen.hbo5.wasdapp.wasdappcore.exception;

public class WasdappRuntimeException extends RuntimeException {

    public WasdappRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public WasdappRuntimeException(Throwable cause) {
        super(cause);
    }

    public WasdappRuntimeException(String message) {
        super(message);
    }
}
