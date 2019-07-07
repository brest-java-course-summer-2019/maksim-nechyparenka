package price;

import java.io.IOException;
import java.math.BigDecimal;

public interface ValueSelector {

    BigDecimal selectValue(BigDecimal value, String path) throws IOException;

}
