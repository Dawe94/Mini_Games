
package com.mini_games;

public class InvalidNumbersException extends Exception {

    public InvalidNumbersException() {
    }

    public InvalidNumbersException(String message) {
        super(message);
    }

    public InvalidNumbersException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidNumbersException(Throwable cause) {
        super(cause);
    }

    public InvalidNumbersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
