package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    static Properties properties;

    static {

        String path= "configuration.properties";
        try {

            FileInputStream fis= new FileInputStream(path);
            properties= new Properties();
            properties.load(fis);


        } catch (IOException e) {
            System.out.println("properties dosyasi okunamadi");

        }

    }

    public static String getProperty(String key){

        return properties.getProperty(key);

    }


}