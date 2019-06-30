import checkdata.CheckInputData;
import file.GetPriceList;
import input.DataInput;
import file.FileReader;

import java.io.*;
import java.math.BigDecimal;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        BigDecimal weight, distance, pricePerKg;
        String exit = "";
        Scanner scanner = new Scanner(System.in);

        FileReader file = new FileReader();
        GetPriceList pricelist = new GetPriceList();

        do {

            input.DataInput input = new DataInput();

            System.out.println("Enter the weight in kilograms or 'q' for quit: ");

            weight = input.dataInput();

            System.out.println("Enter the distance in kilometers or 'q' for quit: ");

            distance = input.dataInput();

            System.out.println("Value of weight = " + weight);
            System.out.println("Value of distance = " + distance);

            pricePerKg = new BigDecimal(file.readFile().getProperty("const.price.per.kg"));

            BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricelist.getPrice(distance)));
            System.out.println("Contract Total Cost is = " + price);

            System.out.println("Do you wish to continue? Make your choice: y / n");
            exit = scanner.next();

        } while (exit.trim().toLowerCase().equals("y"));

        scanner.close();
    }
}