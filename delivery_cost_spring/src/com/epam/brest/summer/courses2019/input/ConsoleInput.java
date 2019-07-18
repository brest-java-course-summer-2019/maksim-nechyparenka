package com.epam.brest.summer.courses2019.input;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleInput {

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

        Scanner scanner = new Scanner(System.in);
        CheckInput check = new CheckInput();
        EnteredValue enteredValue = new IncorrectValue();

        while (enteredValue.getType() == EnteredValue.Types.INCORRECT) {

            enteredValue = check.checkInputValue(scanner.nextLine());

        }
        return enteredValue;
    }
}
