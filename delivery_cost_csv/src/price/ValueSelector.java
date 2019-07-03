package price;

import java.math.BigDecimal;

public interface ValueSelector {

    BigDecimal selectValue(BigDecimal value);

}
