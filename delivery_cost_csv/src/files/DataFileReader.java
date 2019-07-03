package files;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public interface DataFileReader {

    default Map<Integer, BigDecimal> readData(final String filePath) throws IOException {
        return null;
    }

}
