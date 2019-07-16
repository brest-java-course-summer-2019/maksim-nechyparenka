package com.epam.brest.summer.courses2019;

import com.epam.brest.summer.courses2019.calculator.Calculator;
import com.epam.brest.summer.courses2019.price.PriceSelector;
import com.epam.brest.summer.courses2019.input.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class Main {

    private static final String CSV_KM_PATH = "price_per_km.csv";
    private static final String CSV_KG_PATH = "price_per_kg.csv";

    private PriceSelector price;
    private ConsoleInput input;
    private Calculator cost;

    public static void main(String[] args) throws IOException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        Main main = applicationContext.getBean(Main.class);
        main.run();
    }

    @Value("${weight.message}")
    private String messageWeight;

    @Value("${distance.message}")
    private String messageDistance;

    public Main(ConsoleInput input, Calculator cost, PriceSelector price) {
        this.input = input;
        this.cost = cost;
        this.price = price;
    }

        private void run() throws IOException {

            BigDecimal weight, distance;
            String exit;
            Scanner scanner = new Scanner(System.in);

            do {

                System.out.println(messageWeight);
                weight = input.getData();

                System.out.println(messageDistance);
                distance = input.getData();

                BigDecimal pricePerKG = price.selectValue(weight, CSV_KG_PATH);
                BigDecimal pricePerKM = price.selectValue(distance, CSV_KM_PATH);

                System.out.println("Value of weight = " + weight + " kg");
                System.out.println("Value of distance = " + distance + " km");
                System.out.println("Selected price per kg is: " + pricePerKG + " USD");
                System.out.println("Selected price per km is: " + pricePerKM + " USD");

                System.out.println("Total COST = " + cost.calculateContractCost(weight, pricePerKG, distance, pricePerKM) + " USD");

                System.out.println("Do you wish to continue? Make your choice: y / n");
                exit = scanner.next();
                scanner.nextLine();

            } while (exit.trim().toLowerCase().equals("y"));
        }
    }