import calculator.ContractCost;
import files.PriceReader;
import input.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        BigDecimal weight, distance;
        BigDecimal pricePerKg = new BigDecimal("30");
        PriceReader price = new PriceReader();
        Scanner scanner = new Scanner(System.in);
        ConsoleInput input = new ConsoleInput(scanner);

        System.out.println("Enter weight in kg or 'q' for quit:");
        weight = input.getData();

        System.out.println("Enter distance in km or 'q' for quit:");
        distance = input.getData();

        System.out.println("Value of weight = " + weight);
        System.out.println("Value of distance = " + distance);
        System.out.println("Constant price per kg is: " + pricePerKg);

        ContractCost cost = new ContractCost(weight, pricePerKg, distance, price.getPrice(distance));
        System.out.println("Total COST = " + cost);
    }
}