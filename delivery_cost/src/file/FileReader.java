package file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileReader {

    FileInputStream fis;
    static private final String FILE_PATH = "resources/data.properties";
    Properties properties = new Properties();

    public FileReader() {
        this.fis = fis;
        this.properties = properties;
    }

    public Properties readFile() throws IOException {

        try {
            fis = new FileInputStream(FILE_PATH);
            properties.load(fis);
        } catch (FileNotFoundException e) {
            System.out.println("PriceList not found!");
        }

        return properties;
    }
}
