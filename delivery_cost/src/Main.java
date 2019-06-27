import java.io.*;
import java.math.BigDecimal;
import java.util.Properties;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        BigDecimal weight, distance, pricePerKg;

        FileReader file = new FileReader();
        GetPriceList pricelist = new GetPriceList();

        DataInput input = new DataInput();

        System.out.println("Enter the weight in kilograms or 'q' for quit: ");

        weight = input.dataInput();

        System.out.println("Enter the distance in kilometers or 'q' for quit: ");

        distance = input.dataInput();

        System.out.println("Value of weight = " + weight);
        System.out.println("Value of distance = " + distance);

        pricePerKg = new BigDecimal(file.readFile().getProperty("const.price.per.kg"));

        BigDecimal price = weight.multiply(pricePerKg).add(distance.multiply(pricelist.getPrice(distance)));
        System.out.println("Price = " + price);
    }
}


