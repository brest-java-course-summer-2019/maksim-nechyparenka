package input;

import checkdata.CheckInputData;

import java.math.BigDecimal;
import java.util.Scanner;

public class DataInput {

    BigDecimal data;
    CheckInputData check;
    boolean flag = true;
    Scanner scanner = new Scanner(System.in);

    public DataInput() {
        this.data = data;
        this.scanner = scanner;
    }

    public BigDecimal dataInput() {

        String inputString = "";

        while (inputString.isEmpty() || inputString == null || flag) {
            inputString = scanner.nextLine();

            if (inputString.trim().toLowerCase().equals("q")) {
                System.out.println("\nBye!");
                System.exit(0);
                scanner.close();
                return null;

            } else {

                    if (check.checkData(inputString)) {
                        data = new BigDecimal(inputString);
                        flag = false;
                    } else flag = true;
            }
        }

        return data;
    }
}