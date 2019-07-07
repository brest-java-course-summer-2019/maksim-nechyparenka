package price;

import files.CSVFileReader;
import files.DataFileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static java.util.Map.Entry.comparingByKey;


public class PriceSelector implements ValueSelector {

    private DataFileReader fileReader = new CSVFileReader();
    Integer desiredKey;
    private BigDecimal price;

    public BigDecimal selectValue(BigDecimal distance, String path) throws IOException {

        Map<Integer, BigDecimal> Prices = fileReader.readData(path);

        SortedMap<Integer, BigDecimal> sortedPrices = new TreeMap<>(Prices);

        desiredKey = sortedPrices.firstKey();

        if (Prices == null || Prices.isEmpty()) {

            throw new FileNotFoundException("File with prices not found!");

        } else {

            for (Map.Entry<Integer, BigDecimal> priceMap : sortedPrices.entrySet()) {

                if(distance.doubleValue() < desiredKey.doubleValue()) {
                    price = sortedPrices.get(desiredKey);

                } else {

                    if (distance.doubleValue() >= priceMap.getKey().doubleValue()) {
                        price = sortedPrices.get(priceMap.getKey());
                    }
                }
            }
        }

        return price;
    }
}
