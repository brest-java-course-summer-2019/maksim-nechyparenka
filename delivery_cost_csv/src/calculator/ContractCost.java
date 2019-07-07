package calculator;

import java.math.BigDecimal;

public class ContractCost implements Calculator {

    private BigDecimal weight;
    private BigDecimal pricePerKg;
    private BigDecimal distance;
    private BigDecimal pricePerKm;
    private BigDecimal totalCost;

//    public ContractCost() {
//
//        this.weight = weight;
//        this.pricePerKg = pricePerKg;
//        this.distance = distance;
//        this.pricePerKm = pricePerKm;
//        this.totalCost = calculateContractCost(weight, pricePerKg, distance, pricePerKm);
//    }

    @Override
    public BigDecimal calculateContractCost(BigDecimal weight, BigDecimal pricePerKg, BigDecimal distance, BigDecimal pricePerKm) {

        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }

    public BigDecimal getCost() {
        return totalCost;
    }
}
