package com.epam.brest.summer.courses2019.model.exception;

/**
 * The {@code ExceptionResponse} is a class
 * for result of rest incorrect work
 */
public class ExceptionResponse {

    /**
     * Provide a reason of incorrect work
     */
    private String message;

    /**
     * Constructing an empty new object
     */
    public ExceptionResponse() {
    }

    /**
     * Constructing new object with a message argument
     *
     * @param message with a reason of incorrect work
     */
    public ExceptionResponse(String message) {
        this.message = message;
    }

    /**
     * Getting message, describing {@code ExceptionResponse}
     *
     * @return String representing a message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Setting a message to {@code ExceptionResponse}
     * @param message provide a reason of incorrect work
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExceptionResponse {" +
                "message='" + message + '\'' +
                '}';
    }
}
