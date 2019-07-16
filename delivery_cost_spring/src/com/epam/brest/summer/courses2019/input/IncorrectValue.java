package com.epam.brest.summer.courses2019.input;

public class IncorrectValue implements EnteredValue {

    @Override
    public Types getType() {
        return Types.INCORRECT;
    }
}
