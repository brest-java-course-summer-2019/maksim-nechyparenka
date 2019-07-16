package com.epam.brest.summer.courses2019.input;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleInput {

    private CheckInput check = new CheckInput();
    private Scanner scanner;
    BigDecimal input;

    public ConsoleInput() {
        this.scanner = scanner;
    }

    public BigDecimal getData() {

        EnteredValue value = receiveValueFromConsole();

        if (value.getType() != EnteredValue.Types.EXIT) {

            CorrectValue correctValue = (CorrectValue) value;
            return correctValue.getValue();

        } else {

            System.out.println("\nBye!");
            System.exit(0);
            return null;
        }
    }

    private EnteredValue receiveValueFromConsole() {

        EnteredValue enteredValue = new IncorrectValue();

        while (enteredValue.getType() == EnteredValue.Types.INCORRECT) {

            enteredValue = check.checkInputValue(scanner.nextLine());
        }
        return enteredValue;
    }
}
