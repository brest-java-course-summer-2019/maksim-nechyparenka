import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileReader {

    FileInputStream fis;
    Properties pro = new Properties();

    public FileReader() {
        this.fis = fis;
        this.pro = pro;
    }

    public Properties readFile() throws IOException {

        try {
            fis = new FileInputStream("resources/data.properties");
            pro.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("PriceList not found!");
        }

        return pro;
    }
}
