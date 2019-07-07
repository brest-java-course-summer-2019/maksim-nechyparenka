package calculator;

import java.math.BigDecimal;

public class ContractCost implements Calculator {

    private BigDecimal weight;
    private BigDecimal pricePerKg;
    private BigDecimal distance;

    public ContractCost() {
        this.weight = weight;
        this.pricePerKg = pricePerKg;
        this.distance = distance;
    }

    @Override
    public BigDecimal calculateContractCost(BigDecimal weight, BigDecimal pricePerKg, BigDecimal distance, BigDecimal pricePerKm) {

        return weight.multiply(pricePerKg).add(distance.multiply(pricePerKm));
    }
}
