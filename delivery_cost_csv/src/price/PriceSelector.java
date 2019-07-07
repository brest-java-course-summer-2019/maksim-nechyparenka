package price;

import files.CSVFileReader;
import files.DataFileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;


public class PriceSelector implements ValueSelector {

    //static private final String CSV_FILE_PATH = "/price_per_km.csv";

    private DataFileReader fileReader = new CSVFileReader();
    private BigDecimal price;

    public BigDecimal selectValue(BigDecimal distance, String path) throws IOException {

        Map<Integer, BigDecimal> Prices = fileReader.readData(path);

        if (Prices == null || Prices.isEmpty()) {

            throw new FileNotFoundException("File with prices not found!");

        } else {

            for (Map.Entry<Integer, BigDecimal> item : Prices.entrySet()) {

                if (distance.compareTo(BigDecimal.valueOf(item.getKey())) > 0) {
                    break;
                    //price = item.getValue();

                } else price = item.getValue();
            }
        }
        return price;
    }
}
