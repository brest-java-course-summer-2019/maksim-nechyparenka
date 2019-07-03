package price;

import files.CSVFileReader;
import files.DataFileReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class PriceSelector implements ValueSelector {

    static private final String CSV_FILE_PATH = "price_per_km.csv";

    DataFileReader fileReader = new CSVFileReader();
    BigDecimal price;

    public BigDecimal selectValue(BigDecimal distance) throws IOException {

        Map<Integer, BigDecimal> Prices = fileReader.readData(CSV_FILE_PATH);

        if (Prices == null || Prices.isEmpty()) {

            throw new FileNotFoundException("File with prices not found!");

        } else {

            for (Map.Entry<Integer, BigDecimal> item : Prices.entrySet()) {

                if (distance.compareTo(BigDecimal.valueOf(item.getKey())) == -1) {
                    price = item.getValue();

                } else continue;
            }
        }
        return price;
    }
}
