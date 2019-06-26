import java.io.*;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        BigDecimal weight, distance, pricePerKm;
        BigDecimal pricePerKg = new BigDecimal("30");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the weight in kilograms or 'q, Q' for quit: ");
        String inputString = scanner.nextLine();

                if (inputString.equals("Q") || inputString.equals("q")) {
                    System.out.println("\nBye!");
                    return;

                } else {
                    if (inputString.isEmpty()) {
                        System.out.println("Please, input your weight data!");
                        inputString = scanner.nextLine();
                    }
                    weight = new BigDecimal(inputString);

        System.out.println("Enter the distance in kilometers or 'q, Q' for quit: ");
        inputString = scanner.nextLine();

                if (inputString.equals("Q") || inputString.equals("q")) {
                    System.out.println("\nBye!");
                    return;

                } else {
                    if (inputString.isEmpty()) {
                        System.out.println("Please, input your distance data!");
                        inputString = scanner.nextLine();
                    }
                    distance = new BigDecimal(inputString);
                }

            System.out.println("Value of weight = " + weight);
            System.out.println("Value of distance = " + distance);

            //Get properties
            FileInputStream fis;
            Properties pro = new Properties();

            try {
                fis = new FileInputStream("resources/data.properties");
                pro.load(fis);
            } catch (FileNotFoundException e) {
                System.out.println("Price datas not found!");
            }

            if (distance.compareTo(new BigDecimal(100)) == 1) {
            //Get prices
            pricePerKm = new BigDecimal(pro.getProperty("km.price.more100"));
            } else pricePerKm = new BigDecimal(pro.getProperty("km.price.less100"));

            BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
            System.out.println("Price = " + price);
        }
    }
}


