package com.epam.brest.summer.courses2019.input;

public interface EnteredValue {

    enum Types {EXIT, INCORRECT, VALUE}
    Types getType();
}
