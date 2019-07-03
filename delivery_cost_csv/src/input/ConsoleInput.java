package input;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleInput {

    CheckInput check = new CheckInput();
    Scanner scanner;

    public ConsoleInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public BigDecimal getData() {

        EnteredValue value = receiveValueFromConsole();

        if (value.getType() != EnteredValue.Types.EXIT) {

            CorrectValue correctValue = (CorrectValue) value;
            //System.out.println("Value: " + correctValue.getValue());
            return (BigDecimal) correctValue.getValue();

        } else {

            System.out.println("\nBye!");
            System.exit(0);
            return null;
        }
    }

    public EnteredValue receiveValueFromConsole() {

        EnteredValue enteredValue = new IncorrectValue();

        while (enteredValue.getType() == EnteredValue.Types.INCORRECT) {

            enteredValue = check.checkInputValue(scanner.nextLine());
        }
        return enteredValue;
    }
}
