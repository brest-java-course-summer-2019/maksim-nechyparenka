package com.epam.brest.summer.courses2019.price;

import java.io.IOException;
import java.math.BigDecimal;

public interface ValueSelector {

    BigDecimal selectValue(BigDecimal value, String path) throws IOException;

}
