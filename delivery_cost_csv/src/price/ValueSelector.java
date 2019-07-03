package price;

import java.io.IOException;
import java.math.BigDecimal;

public interface ValueSelector {

    BigDecimal selectValue(BigDecimal value) throws IOException;

}
