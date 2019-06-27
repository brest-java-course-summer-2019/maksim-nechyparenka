import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class GetPriceList {

    BigDecimal price;

    public GetPriceList() {
        this.price = price;
    }

    public BigDecimal getPrice(BigDecimal distance) throws IOException {

        BigDecimal price;

        FileReader file = new FileReader();

        if (distance.compareTo(BigDecimal.valueOf(100)) == 1) {
            //Get prices
            price = new BigDecimal(file.readFile().getProperty("km.price.after100"));
        } else price = new BigDecimal(file.readFile().getProperty("km.price.before100"));

        return price;
    }
}
