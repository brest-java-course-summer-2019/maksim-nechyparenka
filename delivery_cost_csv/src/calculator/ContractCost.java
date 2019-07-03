package calculator;

import java.math.BigDecimal;

public class ContractCost implements Calculator {

    private BigDecimal weight;
    private BigDecimal pricePerKg;
    private BigDecimal distance;
    private BigDecimal pricePerKm;
    private BigDecimal totalCost;

    public ContractCost(BigDecimal weight, BigDecimal pricePerKg, BigDecimal distance, BigDecimal pricePerKm) {

        this.weight = weight;
        this.pricePerKg = pricePerKg;
        this.distance = distance;
        this.pricePerKm = pricePerKm;
        this.totalCost = calculateContractCost();
    }

    @Override
    public BigDecimal calculateContractCost() {

        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }

    public BigDecimal getCost() {
        return totalCost;
    }
}
