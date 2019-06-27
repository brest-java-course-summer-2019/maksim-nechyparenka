import java.math.BigDecimal;
import java.util.Scanner;

public class DataInput {

    BigDecimal data;
    Scanner scanner = new Scanner(System.in);

    public DataInput() {
        this.data = data;
        this.scanner = scanner;
    }

    public BigDecimal dataInput() {

        String inputString = "";

        while (inputString.isEmpty() || inputString == null) {
            inputString = scanner.nextLine();
        }

        if (inputString.toLowerCase().equals("q")) {
            System.out.println("\nBye!");
            System.exit(0);

        } else {

            try {
                data = new BigDecimal(inputString);
            } catch (NumberFormatException e) {
                System.out.println("Please, input correct data!");
            }
        }

        return data;
    }
}