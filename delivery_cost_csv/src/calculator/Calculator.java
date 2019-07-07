package calculator;

import java.io.IOException;
import java.math.BigDecimal;

public interface Calculator {

    BigDecimal calculateContractCost(BigDecimal weight, BigDecimal pricePerKg, BigDecimal distance, BigDecimal pricePerKm);

}
