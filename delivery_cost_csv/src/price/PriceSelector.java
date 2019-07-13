package price;

import files.CSVFileReader;
import files.DataFileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;


public class PriceSelector implements ValueSelector {

    private DataFileReader fileReader = new CSVFileReader();
    Integer desiredKey;
    private BigDecimal price;

    public BigDecimal selectValue(BigDecimal value, String filePath) throws IOException {

        SortedMap<Integer, BigDecimal> Prices = new TreeMap<>(fileReader.readData(filePath));

        desiredKey = Prices.firstKey();

        if (Prices == null || Prices.isEmpty()) {

            throw new FileNotFoundException("File with prices not found!");

        } else {

            for (Map.Entry<Integer, BigDecimal> priceMap : Prices.entrySet()) {

                if(value.doubleValue() < desiredKey.doubleValue()) {
                    price = Prices.get(desiredKey);

                } else {

                    if (value.doubleValue() >= priceMap.getKey().doubleValue()) {
                        price = Prices.get(priceMap.getKey());
                    }
                }
            }
        }

        return price;
    }
}
