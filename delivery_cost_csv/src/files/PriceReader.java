package files;

import files.CSVFileReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

public class PriceReader {

    static private final String CSV_FILE_PATH = "resources/price_per_km.csv";

    CSVFileReader fileReader = new CSVFileReader();
    BigDecimal price;

    public BigDecimal getPrice(BigDecimal distance) throws IOException {

        Map<Integer, BigDecimal> Prices = fileReader.readData(CSV_FILE_PATH);
        Set<Map.Entry<Integer, BigDecimal>> entrySet = Prices.entrySet();

        if (Prices == null || Prices.isEmpty()) {

            throw new FileNotFoundException("File with prices per km not found!");

        } else {

            for (Map.Entry<Integer, BigDecimal> item : Prices.entrySet()) {

                if (distance.compareTo(BigDecimal.valueOf(item.getKey())) <= item.getKey()) {

                    price = item.getValue();

                } else {

                    if (distance.compareTo(BigDecimal.valueOf(item.getKey())) >= item.getKey()) {

                        price = item.getValue();

                    } else {

                        price = item.getValue();
                    }
                }
            }


        }
        return price;
    }
}
