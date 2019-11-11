package com.epam.brest.summer.courses2019.rest_app.handler;

import com.epam.brest.summer.courses2019.model.exception.ExceptionResponse;
import com.epam.brest.summer.courses2019.rest_app.exception.DaoException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The Rest error handler catches all errors in the {@code com.epam.brest.summer.courses2019.rest_app} package.
 *
 * @see ResponseEntity
 * @see ResponseEntityExceptionHandler
 */
@ControllerAdvice("com.epam.brest.summer.courses2019.rest_app")
public class RestErrorHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle Dao Runtime Exception
     *
     * @param daoEx is {@code DaoRuntimeException}.
     * @return the response entity with message of exception.
     */
    @ExceptionHandler(value = DaoException.class)
    public final ResponseEntity<ExceptionResponse> handleDaoRuntimeException(DaoException daoEx) {

        ExceptionResponse response = new ExceptionResponse(daoEx.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle empty result data access exception.
     *
     * @param exc is {EmptyResultDataAccessException}.
     * @return the response entity with message of exception.
     */
    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public static ResponseEntity<ExceptionResponse> handleEmptyResultException(EmptyResultDataAccessException exc) {

        ExceptionResponse response = new ExceptionResponse(exc.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle duplicate key exception.
     *
     * @param exc is {DuplicateKeyException}.
     * @return the response entity with message of exception.
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public static ResponseEntity<ExceptionResponse> handleDuplicateException(DuplicateKeyException exc) {

        ExceptionResponse response = new ExceptionResponse("This instance is already exist in DataBase!");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
