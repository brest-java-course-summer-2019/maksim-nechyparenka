package com.epam.brest.summer.courses2019.model.exception;

import org.junit.Assert;
import org.junit.Test;

public class ExceptionResponseTest {

    ExceptionResponse exceptionResponse = new ExceptionResponse();

    @Test
    public void getMessage() {
        exceptionResponse.setMessage("No response!");
        Assert.assertTrue(exceptionResponse.getMessage().equals("No response!"));
    }
}
