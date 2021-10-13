package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static Properties properties = new Properties();


    public static Properties getProperties() {
        try {
            InputStream output = new FileInputStream("src/test/resources/application.properties");
            properties.load(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
