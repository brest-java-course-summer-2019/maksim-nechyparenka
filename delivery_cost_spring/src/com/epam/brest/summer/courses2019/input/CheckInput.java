package com.epam.brest.summer.courses2019.input;

import java.math.BigDecimal;

public class CheckInput {

    private static final String QUIT_SYMBOL = "q";

    public EnteredValue checkInputValue(String inputValue) {

        EnteredValue result = new ExitValue();

        if (!inputValue.trim().toLowerCase().equals(QUIT_SYMBOL)) {

            try {
                BigDecimal value = new BigDecimal(inputValue);

                if (value.compareTo(BigDecimal.ZERO) > 0) {
                    result = new CorrectValue(new BigDecimal(inputValue));

                } else {

                    throw new IllegalArgumentException();
                }

            } catch (IllegalArgumentException e) {

                System.out.format("Incorrect value: %s%n", inputValue);
                result = new IncorrectValue();
            }

        } else {

            System.out.println("\nBye!");
            System.exit(0);
            return null;
        }

        return result;
    }
}
