package com.epam.brest.summer.courses2019.calculator;

import java.math.BigDecimal;

public interface Calculator {

    BigDecimal calculateContractCost(BigDecimal weight, BigDecimal pricePerKg, BigDecimal distance, BigDecimal pricePerKm);

}
