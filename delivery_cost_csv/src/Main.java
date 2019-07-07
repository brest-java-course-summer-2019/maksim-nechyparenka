import calculator.Calculator;
import calculator.ContractCost;
import price.PriceSelector;
import input.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static final String CSV_FILE_PATH = "price_per_km.csv";

    public static void main(String[] args) throws IOException {

        BigDecimal weight, distance;
        BigDecimal pricePerKg = new BigDecimal("30");
        PriceSelector price = new PriceSelector();
        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);

        String exit;

        do {

            System.out.println("Enter weight in kg or 'q' for quit:");
            weight = input.getData();

            System.out.println("Enter distance in km or 'q' for quit:");
            distance = input.getData();

            System.out.println("Value of weight = " + weight + " kg");
            System.out.println("Value of distance = " + distance + " km");
            System.out.println("Constant price per kg is: " + pricePerKg + " USD");

            Calculator cost = new ContractCost();

            System.out.println("Total COST = " + cost.calculateContractCost(weight, pricePerKg, distance, price
                    .selectValue(distance, CSV_FILE_PATH)));

            System.out.println("Do you wish to continue? Make your choice: y / n");
            exit = scanner.next();
            scanner.nextLine();

        } while (exit.trim().toLowerCase().equals("y"));
    }
}