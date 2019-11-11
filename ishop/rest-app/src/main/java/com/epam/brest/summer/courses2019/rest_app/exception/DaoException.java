package com.epam.brest.summer.courses2019.rest_app.exception;

public class DaoException extends RuntimeException {

    public DaoException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DaoException(String s) {
        super(s);
    }
}
